import java.util.*;

public class weightedSampling {

 public static void main(String[] args) {

       // double[] sampling = { 0.4, 0.5, 0.1};
    List<Element> testList = new ArrayList<>();
    testList.add(new Element(.4, "red"));
    testList.add(new Element(.5, "Yello"));
    testList.add(new Element(.1, "Blue"));

    RandomSampler randomSampler = new RandomSampler(testList);

    for(int i = 0; i < 10; i++){
      System.out.println(randomSampler.getRandom().color);
    }
   // System.out.println(sampling);
   // System.output.weightedSampling(10, sampling);
}

  public static class Element {
    protected double probability;
    protected String color;

    public Element (double probability, String name){
      this.probability = probability;
      this.color = color;
    }
  }
  // public static double[] weightedSamping (int n, double[] samples){
    public static class RandomSampler{
      private List<Element> element;
      private Random random = new Random();
      private double totalPro = 0;

      public RandomSampler(List<Element> elements){
        this.elements = elements;
        for (Element element : element){
          totalPro += element.probability;
        }
      }
      public Element getRandom() {
        double index = random.nextDouble();
        double sum = 0;
        while (sum < index) {
          sum += elements.get(i++).probability;
        }
        return elements.get(Math.max(0, i-1));
      }



    }




  }
