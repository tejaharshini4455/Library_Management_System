import java.util.Scanner;	//importing scanner class
import menu.Menu;		     //importing user defined package that prints menu

interface Librarybooks   // Interface
{					
	int comp(Book x, Book y);
	void addbook(Book a);
	void update();
	void search();
	void showallb();
	int avail(int x);
	Book checkoutb(Student x);
}

class Books implements Librarybooks  // Implementing interface
{
	static	//Static Block
	{	
		System.out.println("\t\t WELCOME TO LIBRARY\n");
	}
	final int size=1000;		// final keyword
	Scanner sc=new Scanner(System.in);
	Book []tbooks=new Book[size];
	public int count=0;
	public int comp(Book b1,Book b2)		// public access modifier
	{
	    if(b1.bname==b2.bname)
	    {
	        return 0;
	    }
	    if(b1.sno==b2.sno)
	    {
	        return 0;
	    }
	    return 1;
	}
	
	public void addbook(Book b)		// to add book in library
	{
		for(int i=0;i<count;i++)
		{
			if((this.comp(b,this.tbooks[i]))==0)	//Using this keyword
			{
				System.out.println("Book already exist");
				return;
			}
		}
		if(count<size)
		{
			this.tbooks[count]=b;
			count++;
		}
		else
		{
			System.out.println("no space");
		}
	}

	public void update() //update quantity of books
	{
	    	int sno1;
	    	int nq;
	    	System.out.println("Enter serial no of book: ");
	    	sno1=sc.nextInt();
	    	for(int i=0;i<count;i++)
	    	{
	        	if(sno1==tbooks[i].sno)
	        	{
	           	System.out.println("Enter new quantity of books: ");
	           	nq=sc.nextInt();
			  	tbooks[i].quantity+=nq;
           		return;
        		}
    		}
    		System.out.println("No such book present");
    		return;
	}

	public void search() // to search a book in library
	{
    		int sno2;
    		System.out.println("Enter serial no of book : ");
    		sno2=sc.nextInt();
    		for(int i=0;i<count;i++)
    		{
        		if(sno2==tbooks[i].sno)
        		{
            		System.out.println("Serial NO\tName\tAuthor Name\tQuantity");
            		System.out.println(tbooks[i].sno+"\t\t"+tbooks[i].bname+"\t"+tbooks[i].aname+"\t\t\t"+tbooks[i].quantity);
        			return;
        		}
    		}
    		System.out.println("No such book present");
    		return;    
	}

	public void showallb()  //displays all books in library
	{
    		System.out.println("\n Showing All Books ");
		System.out.println("Serial NO\tName\tAuthor Name\tQuantity");
    		for(int i=0;i<count;i++)
    		{
        		System.out.println(tbooks[i].sno+"\t\t"+tbooks[i].bname+"\t"+tbooks[i].aname+"\t\t\t"+tbooks[i].quantity);
    		}
		if(count==0)
		{
			System.out.println("No Books present ");
		}
	}

	public int avail(int sno)  // to check book is available in library
	{
		for(int i=0;i<count;i++)
		{
			if(sno==tbooks[i].sno)
			{
				if(tbooks[i].quantity>0)
				{
					System.out.println("Book is available");
					return i;
				}
			}
		}
		System.out.println("Book is not available");
		return -1;
	}
	
	public Book checkoutb(Student stu)  // to check out a book 
	{
		System.out.println(stu.bcount);
		System.out.println("Enter serial no of book to be checkedout: ");
		int sno3=sc.nextInt();
		int index=avail(sno3);
		for(int i=0;i<stu.bcount;i++)
		{
			if(stu.borrowed[i].sno==sno3)
			{
				System.out.println("\n You already borrowed this book");
				return null;
			}
		}
		if(index!=-1)
		{
			tbooks[index].quantity--;
			return tbooks[index];
		}
		return null;
	}
	public void checkinb(Book b)
	{
		for(int i=0;i<count;i++)
		{
			if(b.sno==tbooks[i].sno)
			{
				tbooks[i].quantity++;
				return;
			}
		}
	}
}

class Book extends Books			// Inheritance 
{ 			
	Scanner sc=new Scanner(System.in);
	public int sno, quantity;
	public String bname,aname;
	Book()						//constructor 
	{		
		System.out.println("Enter serial no of book: ");
		sno=sc.nextInt();
		System.out.println("Enter quantity of book:");
		quantity=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter book name: ");
		bname=sc.nextLine();
		System.out.println("Enter author name: ");
		aname=sc.nextLine();
	}
}

interface Librarystu	//Interface
{	
	void addstu(Student stu);
	void showalls();
	int valid(String a);
	void checkouts(Books x);
}

class Students implements Librarystu	// Implementing Interface
{	
	Scanner sc=new Scanner(System.in);
	final int n=200;
    	Student []stu=new Student[n];
    	public int scount=0;

    	public void addstu(Student stu)	// to register a new student 
    	{
        	for(int i=0;i<scount;i++)
        	{
            	if(stu.roll.equalsIgnoreCase(this.stu[i].roll))
            	{
                	System.out.println("Student with roll no "+ stu.roll+" already present");
            	}
        	}
        	if(scount<n)
        	{
            	this.stu[scount]=stu;
            	scount++;
        	}
        	return;
    	}
    	public void showalls()		// shows all registered students 
    	{
		if(scount<1)
		{
			System.out.println("No Students present");
		}
		else
		{
        		for(int i=0;i<scount;i++)
        		{
            		System.out.println("Roll NO\tName\tYear");
            		System.out.println(stu[i].roll+"\t"+stu[i].name+"\t"+stu[i].y);
        		}
		}
        	return;
    	}
    	public int valid(String r)	// to check a student is registered
    	{
        	for(int i=0;i<scount;i++)
        	{
            	if(r.equalsIgnoreCase(stu[i].roll))
            	{
                	return i;
            	}
        	}
        	System.out.println("Student not present");
        	return -1;
    	}
    	public void checkouts(Books Book)		// to checkout a student with book
    	{
        	int index;
	   	String rno;
        	System.out.println("Enter your roll no : ");
        	rno=sc.nextLine();
        	index=this.valid(rno);
        	if(index!=-1)
        	{
			Book.showallb();
			Book b=Book.checkoutb(stu[index]);
            	if(b!=null)
            	{
                	if(stu[index].bcount<3)
                	{
                    	stu[index].borrowed[stu[index].bcount]=b;
				  	stu[index].bcount++;
			  		System.out.println("Book checked out");
                	}
                	else
				{
                    	System.out.println("Student cannot borrow more than 3 books");
                	}
            	}   
        	}
		else
		{
        		System.out.println("Book is not available");
		}
    	}
	public void checkins(Books Book) 	// To return a book 
	{
		String rno;
		System.out.println("Enter roll number:");
		rno=sc.nextLine();
		int index=this.valid(rno);
		if(index!=-1)
		{
			Student s=stu[index];
			if(s.bcount==0)
			{
				System.out.println("NO books to return");
				return;
			}
			else
			{
				System.out.println("borrowed books count="+s.bcount);
				System.out.println("Serial NO\tBook Name");
				for(int i=0;i<=s.bcount;i++)
				{
					if(s.borrowed[i]==null)
					{
						continue;
					}
					System.out.println(s.borrowed[i].sno+" \t\t"+s.borrowed[i].bname );
				}
				System.out.println("Enter sno of book to be returned : ");
				int sno=sc.nextInt();
				for(int i=0;i<s.bcount;i++)
				{
				if(s.borrowed[i]!=null)
				{
					if(sno==s.borrowed[i].sno)
					{
						Book.checkinb(s.borrowed[i]);
						s.borrowed[i]=null;
						s.bcount=s.bcount-1;
						return;
					}
				}
				}
				System.out.println("You did not borrow book with sno : "+sno);
			}
		}
	}
}

class Student extends Students		// Inheritance
{
	Scanner sc=new Scanner(System.in);
	public String roll,name;
	public int y,bcount=0;
	Book []borrowed=new Book[3];
	Student()
	{
		System.out.println("Enter name of student:");
		name=sc.nextLine();
		System.out.println("Enter roll no of student: ");
		roll=sc.nextLine();
		System.out.println("Enter year of student : ");
		y=sc.nextInt();
	}
}
public final class Main	// public keyword and final keyword
{	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		Books obj1=new Books();	
		Menu m=new Menu();
		Students obj3=new Students();
		int ch=1;
		while(ch!=0)
		{
			m.dispmenu();
			System.out.println("Enter your choice:");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					Book obj2=new Book();
					obj1.addbook(obj2);
					break;
				case 2:
					obj1.update();
					break;
				case 3:
					obj1.search();
					break;
				case 4:
					obj1.showallb();
					break;
				case 5:
					Student s=new Student();
					obj3.addstu(s);
					break;
				case 6:
					obj3.showalls();
					break;	
				case 7:
					obj3.checkouts(obj1);
					break;
				case 8: 
					obj3.checkins(obj1);
					break;		
			}
		}
	}
}