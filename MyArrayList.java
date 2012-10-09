public class MyArrayList<T>
{
    /*
     * CSC383ArrayList
     * Devon Blandin
     * dblandin@gmail.com
     * CSC383-501
     * generic class for an ArrayList data structure.
     */

    private T[] a;
    private int size;

    // default constructor
    public MyArrayList()
    {
        this.a = (T[]) new Object[10];
    }

    // adds object to the end of the ArrayList
    public boolean add(T object)
    {
        // if size + 1 is less than the length of internal array
        // add the object to the end
        if (this.size + 1 < a.length)
        {
            this.a[this.size()] = object;
        }
        // if size + 1 is larger than the length of internal array
        // resize and add object to the end
        else
        {
            this.resize();
            this.a[this.size()] = object;
        }

        size++;
        return true;
    }

    // adds "object" at index
    // throws IndexOutOfBoundsException if index is greater than size() or less than 0
    public boolean add(int index, T object) throws IndexOutOfBoundsException
    {
        // if index is within bounds
        if (index >= 0 || index < this.size())
        {
            // if size + 1 is greater that the length of the internal array
            // resize the ArrayList
            if (this.size() + 1 > a.length)
                this.resize();

            // for every item at the end of the ArrayList back
            // to the index, move to the right
            for (int i = this.size(); i > index; i--)
                this.set(i, this.get(i-1));

            // set the object to the index
            this.set(index, object);
            size++;
            return true;
        }
        // throw an exception if index is out of bounds
        else
            throw new IndexOutOfBoundsException("index " + index + " is beyond the size of the array (" + this.size() + ")");
    }

    // sets a[index] to object
    // throws IndexOutOfBoundsException if index is greater than size() or less than 0
    public boolean set(int index, T object) throws IndexOutOfBoundsException
    {
        // if index is within bounds
        if (index >= 0 && index < this.size())
        {
            this.a[index] = object;
            return true;
        }
        // throw an exception if index is out of bounds
        else
            throw new IndexOutOfBoundsException("index " + index + " is beyond the size of the array (" + this.size() + ")");
    }

    // returns object at index
    // throws IndexOutOfBoundsException if index is greater than size() or less than 0
    public T get(int index) throws IndexOutOfBoundsException
    {
        // if index is within bounds
        if (index >= 0 && index < this.size())
            return a[index];

        // throw an exception if index is out of bounds
        else
            throw new IndexOutOfBoundsException("index " + index + " is beyond the size of the array (" + this.size() + ")");
    }

    // returns the size of Arraylist
    public int size()
    { return this.size; }

    // returns true if Arraylist is empty,
    // false otherwise
    public boolean isEmpty()
    {
        if (this.size() == 0)
            return true;
        else
            return false;
    }

    // removes and returns object at index
    // throws IndexOutOfBoundsException if index is greater than or equal to size() or less than 0
    public T remove(int index) throws IndexOutOfBoundsException
    {
        // if index is within bounds
        if (index >= 0 && index < this.size())
        {
            T temp = a[index];

            // starting at the index moving until the end of the ArrayList
            // shift objects to the left
            for (int i = index; i < this.size() - 1; i++)
                a[index] = a[index + 1];

            size--;
            return temp;
        }
        // throw an exception if index is out of bounds
        else
            throw new IndexOutOfBoundsException("index " + index + " is beyond the size of the array (" + this.size() + ")");
    }

    // returns true if ArrayList contains object,
    // false otherwise
    public boolean contains(T object)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (this.a[i].equals(object))
                return true;
        }

        return false;
    }

    // returns the index of object if found in array
    // -1 otherwise
    public int indexOf(T object)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (this.a[i].equals(object))
                return i;
        }

        return -1;
    }

    // returns a String representation of the ArrayList
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer("[");

        for (int i = 0; i < this.size(); i++)
        {
            if (i < this.size() - 1)
                stringBuffer.append(this.a[i] + ", ");
            else
                stringBuffer.append(this.a[i]);
        }

        stringBuffer.append("]");

        return stringBuffer.toString();
    }

    // doubles the size of the internal array
    private void resize()
    {
        // create new array with doubled size
        T[] resizedArray = (T[]) new Object[this.a.length * 2];

        // copies elements of internal array into new array
        for (int i = 0; i < this.size(); i++)
            resizedArray[i] = this.a[i];

        // set internal array to new array
        this.a = resizedArray;
    }

    // testing main method
    public static void main(String[] args)
    {

        System.out.println("Testing MyArrayList...\n");

        System.out.println("Creating MyArrayList<Integer> list...");
        MyArrayList<Integer> list = new MyArrayList<Integer>();

        System.out.println("\nPrinting out size of list...");
        System.out.println(list.size());
        System.out.println("\nPrinting out list...");
        System.out.println(list);
        System.out.println("\nChecking if list is empty...");
        System.out.println(list.isEmpty());

        System.out.println("\nAdding 20 random numbers between 1 and 100 to list...");
        for (int i = 0; i < 20; i++)
        {
            int randomNumber = (int) (Math.random() * 100 + 1);
            System.out.print(randomNumber + "/" + list.add(randomNumber) + " ");
            list.add(randomNumber);
        }

        System.out.println("\n\nPrinting out size of list...");
        System.out.println(list.size());
        System.out.println("\nPrinting out list...");
        System.out.println(list);
        System.out.println("\nChecking if list is empty...");
        System.out.println(list.isEmpty());

        int randomNumber = (int) (Math.random() * 100 + 1);
        System.out.println("\nChecking if " + randomNumber + " is in the list...");
        System.out.println(list.contains(randomNumber));

        if (list.contains(randomNumber))
            System.out.println(randomNumber + " is at index " + list.indexOf(randomNumber));
        else
            System.out.println(randomNumber + " is not in the list.");

        randomNumber = (int) (Math.random() * 100 + 1);
        int randomIndex = (int) (Math.random() * list.size());

        try
        {
            System.out.println("\nSetting list[" + randomIndex + "] (" + list.get(randomIndex) + ") to " + randomNumber + "...");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            System.out.println(list.set(randomIndex, randomNumber));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            System.out.println("list[" + randomIndex + "] is now " + list.get(randomIndex) + ".");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        System.out.println("\nPrinting out size of list...");
        System.out.println(list.size());

        System.out.println("\nPrinting out list...");
        System.out.println(list);

        System.out.println("\nAdding 50 more random numbers between 1 and 100 to list...");
        for (int i = 0; i < 50; i++)
        {
            int anotherRandomNumber = (int) (Math.random() * 100 + 1);
            System.out.print(anotherRandomNumber + "/" + list.add(anotherRandomNumber) + " ");
            list.add(anotherRandomNumber);
        }

        System.out.println("\n\nPrinting out size of list...");
        System.out.println(list.size());

        System.out.println("\nPrinting out list...");
        System.out.println(list);

        System.out.println("\nRemoving numbers from 20 random indexes...");
        for (int i = 0; i < 20; i++)
        {
            randomIndex = (int) (Math.random() * list.size());
            try
            {
                list.remove(randomIndex);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }               
        System.out.println(list.size());

        System.out.println("\nPrinting out MyArrayList...");
        System.out.println(list);
    } // end main
} // end CSC383ArrayList class

// thrown when a user provides an index outside of the internal array's size
class IndexOutOfBoundsException extends Exception
{
    IndexOutOfBoundsException(String msg)
    {
        super(msg);
    }
} // end IndexOutOfBoundsException class
