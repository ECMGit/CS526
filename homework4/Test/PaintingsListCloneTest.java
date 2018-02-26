import com.company.ArtworkManagement;
import com.company.Painting;
import com.company.Person;
import com.company.SinglyLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.util.Scanner;


/**
 * A test program, I used Junit previously, and commented out couples of code for satisfy homework requirement;
 */

public class PaintingsListCloneTest {
    //    @Test             //for JUnit
//    public void testCopyPaintingsList(){ //for Junit
    public static void main(String[] args) throws IOException { //if you want to use JUnit please comment out this line
        SinglyLinkedList<Painting> paintingList = new SinglyLinkedList<>();
        Person shen = new Person("Shen", 1994);
        Person samba = new Person("Samba", 1992);
        Person elder = new Person("ZeminJiang", 1926);
        Painting pps1 = new Painting("s120", shen, 1994, "lll", "oil", "paper");
        Painting pps2 = new Painting("s190", samba, 1998, "www", "oil", "paper");
        Painting pps3 = new Painting("s132", elder, 1998, "eee", "oil", "paper");
        Painting pps4 = new Painting("s123", elder, 2001, "Zhongnanhai", "canvas", "paper");
        Painting pps5 = new Painting("s124", elder, 2001, "Hawaii", "canvas", "paper");
        paintingList.addFirst(pps1);
        paintingList.addLast(pps2);
        paintingList.addLast(pps3);
        paintingList.addLast((pps4));
        paintingList.addLast(pps5);
        SinglyLinkedList<Painting> paintingsListClone = ArtworkManagement.copyPaintingsList(paintingList);
        /**
         * if you want to reenable JUnit, please uncomment out next line code,
         * and comment out the code under that line.
         */
        //Assert.assertEquals(paintingList, paintingsListClone);

        printList(paintingList);
        printList(paintingsListClone);
        //modify birth year of an artist;
        Scanner modifyInfo = new Scanner(System.in);
        System.out.print("please enter a id to modify the birth year of its Artist:");
        String modifyID = modifyInfo.next();
        Painting mpainting = ArtworkManagement.searchPainting(modifyID, paintingList);
        if (mpainting != null) {
            paintingList = paintingList.removeElement(mpainting, paintingList);
            Person martist = mpainting.getArtist();
            System.out.print("The assigned artist is: " + martist.getName() + " born in " + martist.getYear() + ".\nPlease enter a new birth year: ");
            int newByear = modifyInfo.nextInt();
            martist.setYear(newByear);
            mpainting.setArtist(martist);
            paintingList.addLast(mpainting);
        } else {
            System.out.println("The painting doesn't exist.");
        }
        printList(paintingList);

        //print result will be different, because we didn't redefine the Clonelist;
        printList(paintingsListClone);

    }


    /**
     * This is a method used for print all elements in a list.
     *
     * @param paintingList
     */
    public static void printList(SinglyLinkedList<Painting> paintingList) {
        for (int i = 0; i < paintingList.size(); i++) {
            Painting painting = paintingList.getElements(i, paintingList);
            Painting.printSummary(painting);
        }


    }


}
