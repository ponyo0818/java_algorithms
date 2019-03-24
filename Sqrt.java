class Sqrt{
    public static void main(String[ ] args) {
        
        double c = 6.0;
        double t = c;
        double err = 1e-15;
        //t is the qrt, c/t is also the qrt, iterate to make them have less differences
        while (Math.abs(t-c/t) > err * t) {
            t = (c/t + t) / 2.0;
            System.out.println("t: " + t);
            System.out.println("c/t: "+ c/t);
        }
    }
}