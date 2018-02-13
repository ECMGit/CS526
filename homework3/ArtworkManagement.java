/**
 * homework3 of Junhao Shen;
 * added 2 method in SinglylinkedList.java
 * date: 02/12/2018
 */


import java.util.Scanner;
import java.io.IOException;

public class ArtworkManagement {

    public static void main(String[] args) throws IOException {
        /**
         * initialize 2 new singlylinkedlist "paintingSinglyLinkedList" and "sculptureSinglyLinkedList"
         *  */
        SinglyLinkedList<Painting> paintingSinglyLinkedList = new SinglyLinkedList<>();
        SinglyLinkedList<Sculpture> sculptureSinglyLinkedList = new SinglyLinkedList<>();

        //test data
        Painting pps1 = new Painting("s120", "shen", 1994, "lll", "oil", "paper");
        Painting pps2 = new Painting("s190", "samba", 1998, "www", "oil", "paper");
        Painting pps3 = new Painting("s132", "Lee", 1994, "eee", "oil", "paper");
        paintingSinglyLinkedList.addFirst(pps1);
        paintingSinglyLinkedList.addLast(pps2);
        paintingSinglyLinkedList.addLast(pps3);

        int flag = 0;

        while (flag == 0) {
            /**
             * print main menu
             */
            System.out.print("Choose a option:\n" +
                    "1. Add an Artwork\n" +
                    "2. Remove an Artwork\n" +
                    "3. Update artwork location\n" +
                    "4. Display artwork information\n" +
                    "5. Display all paintings\n" +
                    "6. Display all sculptures\n" +
                    "7. Exit\n" +
                    "Please enter a number of option above:");//print main menu
            Scanner in = new Scanner(System.in);
            String a = in.next();
            switch (a) {

                /**add an artwork*/
                case "1":

                    System.out.print("Enter id:");  //add id
                    Scanner addid = new Scanner(System.in);
                    String id = addid.next();
                    System.out.print("Please enter the artwork type:");
                    Scanner intype = new Scanner(System.in);
                    String type = intype.next();
                    /**test value of id whether it had already existed*/
                    Scanner inputinfo = new Scanner(System.in);
                    switch (type) {
                        case "painting":
                            ArtworkManagement.addPainting(id, paintingSinglyLinkedList, inputinfo);
                            flag = 0;
                            break;
                        case "sculpture":
                            ArtworkManagement.addSculpture(id, sculptureSinglyLinkedList, inputinfo);
                            flag = 0;
                            break;
                        default:
                            System.out.println("Please enter optional type~");
                            flag = 0;
                            break;
                    }
                    break;
                /**remove an art work*/
                case "2":
                    System.out.print("Enter id:"); //remove id
                    Scanner removeinput = new Scanner(System.in);
                    String remove_id = removeinput.next();
                    System.out.print("Enter artwork type: ");
                    String removeType = removeinput.next();
                    /**
                     * test value of id whether it had already existed,
                     * if existed,execute remove
                     * else, display main menu
                     * */
                    switch (removeType) {
                        case "painting":
                            Painting remove_p = ArtworkManagement.searchPainting(remove_id, paintingSinglyLinkedList);
                            if (remove_p != null) {
                                paintingSinglyLinkedList = paintingSinglyLinkedList.removeElement(remove_p, paintingSinglyLinkedList);
                                System.out.println("The painting has been removed. Now amount of paintings is: "+ paintingSinglyLinkedList.size());
                            } else {
                                System.out.println("We didn't find the painting~");
                            }
                            break;
                        case "sculpture":
                            Sculpture remove_s = ArtworkManagement.searchSculpture(remove_id, sculptureSinglyLinkedList);
                            if (remove_s != null) {
                                sculptureSinglyLinkedList = sculptureSinglyLinkedList.removeElement(remove_s, sculptureSinglyLinkedList);
                                System.out.println("The sculpture has been removed.");
                            } else {
                                System.out.println("We didn't find the sculpture~");
                            }
                            break;
                        default:
                            System.out.println("Please enter optional type~");
                            break;
                    }
                    flag = 0;
                    break;

                /**update given artwork's location*/
                case "3":
                    System.out.print("Enter id:");  //get id
                    Scanner updateinfo = new Scanner(System.in);
                    String uid = updateinfo.next();
                    System.out.print("Please enter the artwork type:");
                    String utype = updateinfo.next();
                    switch (utype) {
                        case "painting":
                            Painting up_p = ArtworkManagement.searchPainting(uid, paintingSinglyLinkedList);
                            if (up_p != null) {
                                paintingSinglyLinkedList = paintingSinglyLinkedList.removeElement(up_p, paintingSinglyLinkedList);
                                System.out.print("Please enter a new location:");
                                String newPloc = updateinfo.next();
                                up_p.setLocation(newPloc);
                                paintingSinglyLinkedList.addLast(up_p);
                                System.out.println("The location has been updated.");
                            } else {
                                System.out.println("We didn't find the painting~");
                            }
                            break;
                        case "sculpture":
                            Sculpture up_s = ArtworkManagement.searchSculpture(uid, sculptureSinglyLinkedList);
                            if (up_s != null) {
                                sculptureSinglyLinkedList = sculptureSinglyLinkedList.removeElement(up_s, sculptureSinglyLinkedList);
                                System.out.print("Please enter a new location:");
                                String newSloc = updateinfo.nextLine();
                                up_s.setLocation(newSloc);
                                sculptureSinglyLinkedList.addLast(up_s);
                                System.out.println("The sculpture has been updated.");
                            } else {
                                System.out.println("We didn't find the sculpture~");
                            }
                            break;
                        default:
                            System.out.println("Please enter optional type~");
                            break;
                    }
                    break;

                /**display given artwork's information*/
                case "4":
                    System.out.print("Enter id:");  //add id
                    Scanner displayinfo = new Scanner(System.in);
                    String disid = displayinfo.next();
                    System.out.print("Please enter the artwork type:");
                    String distype = displayinfo.next();
                    switch (distype) {
                        case "painting":
                            Painting dispainting = ArtworkManagement.searchPainting(disid, paintingSinglyLinkedList);
                            Painting.printSummary(dispainting);
                            break;
                        case "sculpture":
                            Sculpture disculpture = ArtworkManagement.searchSculpture(disid, sculptureSinglyLinkedList);
                            Sculpture.printSummary(disculpture);
                            break;
                        default:
                            System.out.println("Please enter optional type~");
                            break;
                    }
                    break;

                /**display all paintings*/
                case "5":
                    if (!paintingSinglyLinkedList.isEmpty()) {
                        for (int i = 0; i < paintingSinglyLinkedList.size(); i++) {
                            Painting painting = paintingSinglyLinkedList.getElements(i, paintingSinglyLinkedList);
                            Painting.printSummary(painting);
                        }
                    } else {
                        System.out.println("The list of painting is empty!");
                    }

                    flag = 0;
                    break;

                /**display all sculpture*/
                case "6":
                    if (!sculptureSinglyLinkedList.isEmpty()) {
                        for (int i = 0; i < sculptureSinglyLinkedList.size(); i++) {
                            Sculpture sculpture = sculptureSinglyLinkedList.getElements(i, sculptureSinglyLinkedList);
                            Painting.printSummary(sculpture);
                        }
                    } else {
                        System.out.println("The list of sculpture is empty!");
                    }
                    flag = 0;
                    break;


                case "7":
                    System.out.println("Exited");
                    flag = 1;
                    break;
                //else do loop
                default:
                    System.out.println("Please enter a optional number~");
            }
        }

    }
    /**
     * search element in list through id,
     *
     * return found element; if not found, return none;
     * */
    public static Painting searchPainting(String id, SinglyLinkedList<Painting> paintingSinglyLinkedList) {
        Painting s_painting = null;
        for (int m = 0; m < paintingSinglyLinkedList.size(); m++) {
            if (id.equals(paintingSinglyLinkedList.getElements(m, paintingSinglyLinkedList).getId())) {
                s_painting = paintingSinglyLinkedList.getElements(m, paintingSinglyLinkedList);
                break;
            }
        }
        return s_painting;

    }

    public static Sculpture searchSculpture(String id, SinglyLinkedList<Sculpture> sculptureSinglyLinkedList) {
        Sculpture s_sculpture = null;
        for (int m = 0; m < sculptureSinglyLinkedList.size(); m++) {
            if (id.equals(sculptureSinglyLinkedList.getElements(m, sculptureSinglyLinkedList).getId())) {
                s_sculpture = sculptureSinglyLinkedList.getElements(m, sculptureSinglyLinkedList);
                break;
            }
        }
        return s_sculpture;

    }

    /**
     * add element into list;
     * @param id
     * @param paintingSinglyLinkedList
     * @param inputinfo
     */
    public static void addPainting(String id, SinglyLinkedList<Painting> paintingSinglyLinkedList, Scanner inputinfo) {
        System.out.print("artist: ");
        String artist = inputinfo.next();
        System.out.print("year: ");
        int year = inputinfo.nextInt();
        if (year > 0) {
            System.out.print("location: ");
            String location = inputinfo.next();
            System.out.print("paint type: ");
            String paintType = inputinfo.next();
            System.out.print("material: ");
            String material = inputinfo.next();
            Painting painting = new Painting(id, artist, year, location, paintType, material);
            paintingSinglyLinkedList.addLast(painting);
            System.out.println("New artwork has been saved.");
        } else {
            System.out.println("Please enter a positive number~");

        }
    }

    public static void addSculpture(String id, SinglyLinkedList<Sculpture> sculptureSinglyLinkedList, Scanner inputinfo) {
        System.out.print("artist: ");
        String artist = inputinfo.next();
        System.out.print("year: ");
        int year = inputinfo.nextInt();
        if (year > 0) {
            System.out.print("location: ");
            String location = inputinfo.next();
            System.out.print("material: ");
            String materialOfscul = inputinfo.next();
            System.out.print("height: ");
            double height = inputinfo.nextDouble();
            if (height > 0) {
                System.out.print("weight: ");
                double weight = inputinfo.nextDouble();
                if (weight > 0) {
                    Sculpture sculpture = new Sculpture(id, artist, year, location, materialOfscul, height, weight);
                    sculptureSinglyLinkedList.addLast(sculpture);
                    System.out.println("New artwork has been saved.");
                }
                System.out.println("Please enter a positive number~");
            }
            System.out.println("Please enter a positive number~");
        }
        System.out.println("Please enter a positive number~");
    }

}

