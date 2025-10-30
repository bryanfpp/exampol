public class Rectangle {
    private double base;
    private double altura;

    public Rectangle(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcularSuperficie() {
        return base * altura;
    }

    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(5, 3);
        System.out.println("Superficie: " + r.calcularSuperficie());
        System.out.println("Perímetro: " + r.calcularPerimetro());
    }
}
