public class Bagdriver {
    public static void main(String[] args) {
        //new bag objects
        ResizableArrayBag<Character> bag1 = new ResizableArrayBag<>();
        ResizableArrayBag<Character> bag2 = new ResizableArrayBag<>();
        LinkedBag<Character> bag3 = new LinkedBag<>();
        LinkedBag<Character> bag4 = new LinkedBag<>();

        //adding characters to bag 1 and bag 2
        bag1.add('a');
        bag1.add('a');
        bag1.add('b');
        bag1.add('c');
        bag1.add('d');
        bag1.add('e');
        bag2.add('a');
        bag2.add('c');
        bag2.add('c');
        bag2.add('d');
        bag2.add('f');
        bag2.add('g');

        bag3.add('a');
        bag3.add('a');
        bag3.add('b');
        bag3.add('c');
        bag3.add('d');
        bag3.add('e');
        bag4.add('a');
        bag4.add('c');
        bag4.add('c');
        bag4.add('d');
        bag4.add('f');
        bag4.add('g');
        
        //BagInterface objects to store the bags with the Union, Intersection, and Difference
        BagInterface<Character> rabUnionBag = bag1.union(bag2);
        BagInterface<Character> rabIntersectionBag = bag1.intersection(bag2);
        BagInterface<Character> rabDifferenceBag = bag1.difference(bag2);

        BagInterface<Character> lbUnionBag = bag3.union(bag4);
        //BagInterface<Character> lbIntersectionBag = bag3.intersection(bag4);
        //BagInterface<Character> lbDifferenceBag = bag3.difference(bag4);

        //Objects that turns the BagInterface objects into arrays
        Object[] RABunion= rabUnionBag.toArray();
        Object[] RABintersection = rabIntersectionBag.toArray();
        Object[] RABdifference = rabDifferenceBag.toArray();

        Object[] LBunion = lbUnionBag.toArray();
        //Object[] LBintersection = lbIntersectionBag.toArray();
        //Object[] LBdifference = lbDifferenceBag.toArray();

        //reads out the newly constructed arrays with the Union, Interfacem and Difference algorithms
        System.out.println("Resizable Array Bag: Union\n----------------------------");
        for (int i = 0; i < rabUnionBag.getCurrentSize(); i++) {
            System.out.print(RABunion[i] + " ");
        }
        System.out.println("\n----------------------------\nResizable Array Bag: Intersection\n----------------------------");
        for (int i = 0; i < rabIntersectionBag.getCurrentSize(); i++) {
            System.out.print(RABintersection[i] + " ");
        }
        System.out.println("\n----------------------------\nResizable Array Bag: Difference\n----------------------------");
        for (int i = 0; i < rabDifferenceBag.getCurrentSize(); i++) {
            System.out.print(RABdifference[i] + " ");
        }
        System.out.println("\n----------------------------\nLinked Bag: Union\n----------------------------");
        for (int i = 0; i < lbUnionBag.getCurrentSize(); i++) {
            System.out.print(LBunion[i] + " ");
        }
        /* System.out.println("\n----------------------------\nLinked Bag: Intersection\n----------------------------");
        for (int i = 0; i < lbIntersectionBag.getCurrentSize(); i++) {
            System.out.print(LBintersection[i] + " ");
        } */
        /*System.out.println("\n----------------------------\nLinked Bag: Difference\n----------------------------");
        for (int i = 0; i < lbDifferenceBag.getCurrentSize(); i++) {
            System.out.print(LBdifference[i] + " ");
        } */

    } //end main
} //end Bagdriver


/*
 *  Time Complexity In the Best Case                                Time Complexity In the Worst Case
 *  Resizable Array Bag                                             Resizable Array Bag
 * -------------------------------                                  -------------------------------
 *      Union                                                           Union
 *      Intersection                                                    Intersection
 *      Difference                                                      Difference
 *  Linked Bag                                                      Linked Bag
 * -------------------------------                                  -------------------------------
 *      Union               O(n)                                        Union
 *      Intersection        O(n)                                        Intersection
 *      Difference          O(n)                                        Difference
 */
