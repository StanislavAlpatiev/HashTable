//Stanislav Alpatiev stal5991


/*
 * Denna klass ska förberedas för att kunna användas som nyckel i en hashtabell. 
 * Du får göra nödvändiga ändringar även i klasserna MyString och ISBN10.
 * 
 * Hashkoden ska räknas ut på ett effektivt sätt och följa de regler och 
 * rekommendationer som finns för hur en hashkod ska konstrueras. Notera i en 
 * kommentar i koden hur du har tänkt när du konstruerat din hashkod.
 */
public class Book {
	private MyString title;
	private MyString author;
	private ISBN10 isbn;
	private MyString content;
	private int price;

	public Book(String title, String author, String isbn, String content, int price) {
		this.title = new MyString(title);
		this.author = new MyString(author);
		this.isbn = new ISBN10(isbn);
		this.content = new MyString(content);
		this.price = price;
	}

    //Equals kollar om det andra objektet också är en instans av Book
    //Ifall det är det så jämför den om deras isbn är samma. 
    //För att det ska vara samma så måste vi överlagra hashcode och equalsbetoden hos ISBN10.
	@Override
	public boolean equals(Object rhs) {
		return rhs instanceof Book && isbn.equals(((Book)rhs).isbn);
	}

	//Valt att använda isbn för hashcode eftersom det alltid är unikt per definition.
	//Att använda en stäng till hashcode är inte alltid en bra idee eftersom sekvenser
	//Av bokstäver i ord inte är lika slumpade som man det ser ut. Därför är sanoligheten 
	//för kolision hög bland stängar som inte är slumpade sekvenser av chars 
	//utan ord ur ett naturligt språk.
	//Dessutom så är inte tittlar unika hos böker utan kan vara delade av flara.
	//Samma gäller för författare och pris.
	//Det går att kombinera flera av dessa saker för att skapa en mer unik hashcode
	//Men det är onödigt i detta fall då isbn både är unikt för en bok.
	//Därför lämpar isbn sig bra som hashcode i Book.
	@Override
	public int hashCode() {
		return isbn.hashCode();
	}

	public MyString getTitle() {
		return title;
	}

	public MyString getAuthor() {
		return author;
	}

	public ISBN10 getIsbn() {
		return isbn;
	}

	public MyString getContent() {
		return content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("\"%s\" by %s Price: %d ISBN: %s lenght: %s", title, author, price, isbn,
				content.length());
	}
}
