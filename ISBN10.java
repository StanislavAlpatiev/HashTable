//Stanislav Alpatiev stal5991

public class ISBN10 {

	private char[] isbn;

	public ISBN10(String isbn) {
		if (isbn.length() != 10)
			throw new IllegalArgumentException("Wrong length, must be 10");
		if (!checkDigit(isbn))
			throw new IllegalArgumentException("Not a valid isbn 10");
		this.isbn = isbn.toCharArray();
	}

	//Hashcode för isbn består av en stäng där man bara tar med de char som finns på de udda positionerna.
	// dvs 1, 3, 5 etc. Dock så börjar vi räkna från index 0.
	// Det blir därför från index 0, 2, 4...
	// Detta är för att isbn annars blir för stort för att använda för hashning och skulle ge NumberFormatException.
	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < isbn.length - 1; i += 2){
			sb.append(isbn[i]);
		}
		return Integer.parseInt(sb.toString());
	}

    //Equals jämför två isbn genom att omvandla dem till strängar som sedan jämförs
    //Med hjälp av equals.
    //För att vi skulle kunna komma åt isbn hos objektet other så behöver vi implementera getIsbn()
    @Override
	public boolean equals(Object other) {
		return this.toString().equals(((ISBN10)other).toString());
	}

	private boolean checkDigit(String isbn) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
		}
		int checkDigit = (11 - (sum % 11)) % 11;

		return isbn.endsWith(checkDigit == 10 ? "X" : "" + checkDigit);
	}

	@Override
	public String toString() {
		return new String(isbn);
	}
}
