public class Person{
    private int wiek;
    private String name;
    public Person(){};
    public Person(int wiek, String name){
        try {
            setWiek(wiek);
        }
        catch (InvalidAgeException e){
            System.out.println("Exception: "+e.getMessage());
            return;
        }
        this.name = name;
    }

    public int getWiek() {
        return wiek;
    }

    public String getName() {
        return name;
    }

    public void setWiek(int wiek) throws InvalidAgeException{
        if (wiek < 0 || wiek > 100) {
            throw new InvalidAgeException("Podano niepoprawny wiek");
        }
        this.wiek = wiek;
    }

    public void setName(String name) {
        this.name = name;
    }
}
