SET ROLE TO prodenta
;

DROP TABLE aetna.recare_reminder_feed
;

CREATE TABLE aetna.recare_reminder_feed (
      member_recare_id                              VARCHAR(255)
    , subscriber_id                                 VARCHAR(255)
    , member_id                                     VARCHAR(255)
    , member_fname                                  TEXT
    , contact_fname                                 TEXT
    , recare_start_date                             DATE
    , recare_end_date                               DATE
    , company_display_id                            VARCHAR(255)
    , external_group_id                             VARCHAR(255)
    , group_name                                    VARCHAR(255)
    , plan_id                                       VARCHAR(255)
    , plan_display_id                               VARCHAR(255)
    , external_plan_id                              VARCHAR(255)
    , plan_name                                     VARCHAR(255)
    , recare_frequency_mo                           INT
    , plan_benefit_start_date                       DATE
    , plan_benefit_end_date                         DATE
    , member_eligibility_start                      DATE
    , member_eligibility_end                        DATE
    , member_effective_start_date                   DATE
    , member_effective_end_date                     DATE
    , member_type                                   VARCHAR(255)
    , member_relationship                           VARCHAR(255)
    , send_to_whom                                  VARCHAR(255)
    , send_to_relationship                          VARCHAR(255)
    , send_to_member_type                           VARCHAR(255)
    , send_to_member_id                             VARCHAR(255)
    , send_to_email_domain                          VARCHAR(255)
    , send_to_email_address                         TEXT
    , send_to_email_is_valid_format                 BOOLEAN
    , send_to_first_name_in_email                   BOOLEAN
    , send_to_last_name_in_email                    BOOLEAN
    , send_to_is_last_name_valid_length             BOOLEAN
    , is_send_to_email_address_unique               BOOLEAN
    , is_send_to_unsubscribed_from_benefit_reminder BOOLEAN
    , privacy_restriction                           BOOLEAN
    , regulatory_restriction                        BOOLEAN
    , unsubscribe_preference                        BOOLEAN
    , is_recare_eligible                            BOOLEAN
    , navigator_url                                 VARCHAR(255)
    , navigator_login_url                           VARCHAR(255)
    , last_routine_claim_id                         VARCHAR(255)
    , last_routine_claim_dos                        VARCHAR(255)
    , last_routine_claim_display                    VARCHAR(255)
    , last_routine_ap                               VARCHAR(255)
    , last_routine_pra                              VARCHAR(255)
    , last_routine_pro                              VARCHAR(255)
    , date_created                                  TIMESTAMP WITH TIME ZONE
    , last_updated                                  TIMESTAMP WITH TIME ZONE
)
;

rollback
;

drop table if exists aetna.recare_email
;

create table aetna.recare_email (
      id                    bigserial   not null primary key
    , display_id            UUID unique ----UUID -----
    , recare_email_type     varchar(255) --(recare_w_provider/recare_generic/recare_family)
    , send_to_member_ref    varchar(255)
    , send_to_member_fname  varchar(255)
    , send_to_email_domain  varchar(255)
    , send_to_email_address text
    , navigator_login_url   varchar(255)
    , email_timestamp       timestamp with time zone
    , date_created          TIMESTAMPTZ NOT NULL DEFAULT now()
)
;

drop table if exists aetna.recare_email_member
;

create table aetna.recare_email_member (
      id                         serial8
    , display_id                 uuid unique
    , recare_email_id            bigint      not null references aetna.recare_email (id)
    , recare_email_display_id    uuid        not null references aetna.recare_email (display_id)
    , member_recare_id           varchar(255)
    , subscriber_id              varchar(255)
    , member_id                  varchar(255)
    , recare_start_date          DATE
    , member_relationship        varchar(255)
    , last_routine_claim_id      varchar(255)
    , last_routine_claim_dos     DATE
    , last_routine_claim_display varchar(255)
    , last_routine_ap            varchar(255)
    , last_routine_pra           varchar(255)
    , last_routine_pro           varchar(255)
    , date_created               TIMESTAMPTZ NOT NULL DEFAULT now()
)
;

drop table if exists _temp_aetna_recare_email_all
;

--ALL Eligible recare records:
CREATE TEMPORARY TABLE _temp_aetna_recare_email_all AS
    WITH last_recare_send AS (
        SELECT DISTINCT ON (rem.member_id) rem.member_id
            , re.email_timestamp IS NULL OR
              (now() :: DATE - re.email_timestamp) < interval '6 months'
            AS send_less_than_6_month
        from aetna.recare_email_member rem
            join aetna.recare_email re
                on rem.recare_email_id = re.id
        ORDER by rem.member_id
            , case when re.email_timestamp IS NULL
            THEN 0
              else 1 END
            , re.email_timestamp DESC
    )
    SELECT *
        , row_number()
          over (
              partition by send_to_member_id
              ORDER BY CASE
                       WHEN send_to_relationship = 'SELF'
                           THEN 0
                       WHEN send_to_relationship = 'CHILD'
                           THEN 1
                       ELSE 999
                       END ) AS dense_rk_id
        , row_number()
          over ()            as rn
    FROM aetna.recare_reminder_feed rrf
    WHERE NOT exists(
        SELECT TRUE
        FROM aetna.recare_email_member rem
        WHERE rem.member_recare_id = rrf.member_recare_id
    )
          AND NOT exists(
        SELECT TRUE
        FROM last_recare_send l
        WHERE l.member_id = rrf.member_id
              AND send_less_than_6_month IS TRUE
    )
;

select *
from aetna.recare_email
;

update aetna.recare_email
set email_timestamp = '2017-06-01'
;

;

update aetna.recare_email_member
set member_recare_id = 'aaa'
;

drop table if exists _temp_aetna_recare_email_to_send
;

create temporary table _temp_aetna_recare_email_to_send AS
--SEND ONE PER send_to_member_id
    with uniqe_to_send AS (
        select distinct on (send_to_member_id) *
        from _temp_aetna_recare_email_all
        order by send_to_member_id,
            CASE
            WHEN send_to_relationship = 'SELF'
                THEN 0
            WHEN send_to_relationship = 'CHILD'
                THEN 1
            ELSE 999
            END
    )
--MULTIPLE RECARE OR NOT
        , one_or_multiple as
    (select send_to_member_id
         , count(*) > 1 as multiple_recare
     from _temp_aetna_recare_email_all
     group by send_to_member_id)
        , combine as (
        select uts.*
            , multiple_recare
        from uniqe_to_send uts
            join one_or_multiple oom
            using (send_to_member_id)
    )
--CHECK LAST SURVEY RECORDS PER MEMBER & PROVIDER ID
        , last_survey AS (
        select distinct on (contact_member_ref, provider_display_id) contact_member_ref
            , provider_display_id
            , recommend
        from aetna.survey
        where provider_display_id IS NOT NULL
        order by contact_member_ref
            , provider_display_id
            , date_created desc
    )
--SURVEY RECOMMENDED
        , survey_recommended AS (
        select contact_member_ref
            , provider_display_id
        from last_survey
        where recommend IS NOT FALSE
    )
--GET EMAIL TYPE AND FILTER EMAIL
    select c2.*
        , case when c2.multiple_recare is true
        then 'recare_family' :: varchar
          when su.contact_member_ref is not null
              then 'recare_w_provider' :: varchar
          else 'recare_generic' end as email_type
        , row_number()
          over ()                   as rn_2
    from combine c2
        left join survey_recommended su
            on c2.send_to_member_id = su.contact_member_ref
               and c2.last_routine_pro = su.provider_display_id
               and c2.multiple_recare is false
;

select *
from _temp_aetna_recare_email_to_send
;

select '9bf11df4323181a515058755c2e13ddd8f5' :: uuid
-- , 'bf11f43-7231-81a5-1505-8755c2e138f58755c2e138f58755c2e138f5' :: UUID
;


;

start transaction
;

rollback
;

with display_id_rand AS (
    SELECT random() AS rand_input
    FROM generate_series(
        1
        , 10 * (
            select count(*)
            from _temp_aetna_recare_email_to_send)
    )
)
    , new_display_ids AS (
    SELECT md5(rand_input :: TEXT || clock_timestamp() :: TEXT) :: UUID
        AS display_id
    FROM display_id_rand
)
    , display_id_checked AS (
    select display_id
        , row_number()
          over () as rn_2
    from new_display_ids n
    where not exists(
        select true
        from aetna.recare_email re
        where re.display_id = n.display_id
    )
    group by display_id
)
    , to_insert as (
    insert into aetna.recare_email (recare_email_type, display_id, send_to_member_ref, send_to_member_fname, send_to_email_domain, send_to_email_address, navigator_login_url)
        select email_type
            , display_id
            , member_id
            , contact_fname
            , send_to_email_domain
            , send_to_email_address
            , navigator_login_url
        from _temp_aetna_recare_email_to_send t
            join display_id_checked d
            using (rn_2)
    returning id, send_to_member_ref, display_id
)
    , display_id_rand_member AS (
    SELECT random() AS rand_input
    FROM generate_series(
        1
        , 10 * (
            select count(*)
            from _temp_aetna_recare_email_all)
    )
)
    , new_display_ids_member AS (
    SELECT md5(rand_input :: TEXT || clock_timestamp() :: TEXT) :: UUID
        AS display_id
    FROM display_id_rand_member
)
    , display_id_checked_member AS (
    select display_id
        , row_number()
          over () as rn
    from new_display_ids_member n
    where not exists(
        select true
        from aetna.recare_email_member re
        where re.display_id = n.display_id
    )
    group by display_id
)
insert into aetna.recare_email_member (display_id, recare_email_id, recare_email_display_id, member_recare_id, subscriber_id, member_id, recare_start_date, member_relationship, last_routine_claim_id, last_routine_claim_dos, last_routine_claim_display, last_routine_ap, last_routine_pra, last_routine_pro)
    select d.display_id
        , ti.id
        , ti.display_id
        , member_recare_id
        , subscriber_id
        , member_id
        , recare_start_date
        , member_relationship
        , last_routine_claim_id
        , last_routine_claim_dos :: DATE
        , last_routine_claim_display
        , last_routine_ap
        , last_routine_pra
        , last_routine_pro
    from _temp_aetna_recare_email_all a
        join to_insert ti
            on a.send_to_member_id = ti.send_to_member_ref
        join display_id_checked_member d
        using (rn)

;

start transaction
;

rollback
;

commit
;

truncate table aetna.recare_email cascade
;

;

select *
from aetna.recare_email
;

;

select *
from aetna.recare_email_member
;

rollback
;

select *
from _temp_aetna_recare_email_all
;

;

select distinct send_to_relationship
from _temp_aetna_recare_email_all
;

-----ALL RECARE RECORDS HAVE EMAIL SENT:

/*
AETNA RECARE EMAIL LOGIC - STORED PROC
    SCHEMA
        aetna.recare_email
            id serial8
            display_id uuid
            recare_email_type varchar (recare_w_provider/recare_generic/recare_family)
            send_to_member_ref
            send_to_member_fname
            send_to_email_domain
            send_to_email_address
            navigator_login_url
            email_timestamp (default null)
            date_created (default now)

        aetna.recare_email_member
            id serial8
            display_id uuid
            recare_email_id (fk to aetna.recare_email(id))
            recare_email_display_id (reference to aetna.recare_email(display_id))
            member_recare_id
            subscriber_id
            member_id
            recare_start_date
            member_relationship
            last_routine_claim_id
            last_routine_claim_dos
            last_routine_claim_display
            last_routine_ap
            last_routine_pra
            last_routine_pro
            date_created

    LOGIC
        Eligible Recare Records for email;
            haven't sent email for same recare record (recorded in recare_email_member)
            haven't sent recare email for same member within prior [recare_frequency_mo] months
        Recare consolidation - communicate to one member
            aggregate all eligible recare records for emails on the send_to_member_id
        Recare Email type determination
            if sending for one member:
                if there is a last_routine_provider for the recare, check if the member has left survey feedback
                if no feedback or positive then send "recare_w_provider" email
                if no last_routine_provider or if negative feedback, then send "recare_generic" email
            if sending for multiple members, then send "recare_family" email
        EMAIL VALIDATION & FILTERS
        Eligible emails to communicate to (same as survey)
            Email validation
                email is valid format
                email is unique
                email domain not filtered
                email domain whitelisted OR email meets name requirements
            Not unsubscribed
        Lower environment filter
            Only send to non-brighter.com domains in prod (filter anything but @brighter.com in lower envs)
    PROCESSING
        Calculate all new recares eligible for emails
        Aggregate to one email to be sent (retain underlying recares for reference)
        Insert one record per email to be sent into aetna.recare_email
        Insert all associated recare records into aetna.recare_email_member associated to the single email record in aetna.recare_email
*/


SELECT *
FROM aetna.recare_reminder_feed
;

start transaction
;

SELECT *
from koko.sp_migrate_access_point_fee_schedule_single_access_point('BXAXQJQH58871332', 'BPTNVLJV68324696',
                                                                   'RKIDWUCL51532481')
;

WITH display_id_rand AS (
    SELECT random() AS rand_input
    FROM generate_series(
        1
        , (10 * 10)
    )
)
    , new_display_ids AS (
    SELECT md5(rand_input :: TEXT || clock_timestamp() :: TEXT
           ) :: UUID
        AS display_id
    FROM display_id_rand
)
select * -- display_id, length(display_id)
from new_display_ids
group by display_id
;

select *
from uuid_generate_v4()
;

select 1
    , *
from koko.fn_practice_mapping('RKIDWUCL51532481')
union all
select 2
    , *
from koko.fn_practice_mapping('BPTNVLJV68324696')
;

;

commit
;

select *
from colo.npi_
;

SELECT *
FROM colo.cigna_provider_output
WHERE action = 'A'
;


WITH uniq_member AS
(SELECT member_id
 FROM aetna.member_lite
 GROUP BY member_id
 HAVING count(*) = 1)
    , member AS (
    SELECT ml.member_id
        , ml.send_to_whom
        , ml.is_send_to_unsubscribed_from_benefit_reminder
        , ml.member_type
        , ml.is_recare_eligible
        , ml.member_fname
        , ml.display_name AS contact_fname
    FROM aetna.member_lite ml
        JOIN uniq_member um
            ON ml.member_id = um.member_id
    WHERE nullif(trim(ml.send_to_email_address), '') IS NOT NULL
          AND nullif(trim(ml.send_to_email_domain), '') IS NOT NULL
          AND ml.send_to_email_is_valid_format = TRUE
          AND ml.is_recare_eligible = TRUE
)
    , member_recare as (
    SELECT *
    FROM aetna.member_recare
    Where recare_end_date IS NULL)
select member_recare_id
    , subscriber_id
    , mr.member_id
    , member_fname
    , contact_fname
    , recare_start_date
    , recare_end_date
    , company_display_id
    , external_group_id
    , group_name
    , plan_id
    , plan_display_id
    , external_plan_id
    , plan_name
    , plan_benefit_start_date
    , plan_benefit_end_date
    , member_eligibility_start
    , member_eligibility_end
    , member_effective_start_date
    , member_effective_end_date
    , member_type
    , member_relationship
    , send_to_whom
    , send_to_relationship
    , send_to_member_type
    , send_to_member_id
    , send_to_email_domain
    , send_to_email_address
    , send_to_email_is_valid_format ----
    , send_to_first_name_in_email ----
    , send_to_last_name_in_email  ----
    , send_to_is_last_name_valid_length---
    , is_send_to_email_address_unique---
    , is_send_to_unsubscribed_from_benefit_reminder
    , privacy_restriction
    , regulatory_restriction
    , unsubscribe_preference
    , is_recare_eligible
    , navigator_url
    , navigator_login_url
    , recare_frequency_mo
    , last_routine_claim_non_pred        as last_routine_claim_id
    , last_routine_dos_non_pred          as last_routine_claim_dos
    , last_routine_display_name_non_pred as last_routine_claim_display
    , last_routine_ap_non_pred           as last_routine_ap
    , last_routine_pra_non_pred          as last_routine_pra
    , last_routine_pro_non_pred          as last_routine_pro
    , last_updated
    , date_created
FROM member_recare mr
    JOIN member
        ON mr.member_id = member.member_id