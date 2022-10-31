public class Term {
    public int coef = 0;
    public int exp = 0;

    @Override
    public String toString(){
        return String.format("%d %d", coef, exp);
    }
}
