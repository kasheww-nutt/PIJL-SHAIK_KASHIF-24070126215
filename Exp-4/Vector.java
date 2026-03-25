public class Vector {

    private double[] components;
    private int dimension;

    public Vector(double... components) throws VectorException {
        if (components.length != 2 && components.length != 3) {
            throw new VectorException(
                "Invalid dimension: " + components.length + ". Only 2D or 3D vectors are allowed."
            );
        }
        this.components = components;
        this.dimension = components.length;
    }

    private void checkDimensions(Vector other) throws VectorException {
        if (this.dimension != other.dimension) {
            throw new VectorException(
                "Dimension mismatch: Cannot operate on a " + this.dimension +
                "D vector with a " + other.dimension + "D vector."
            );
        }
    }

    public Vector add(Vector other) throws VectorException {
        checkDimensions(other);
        double[] result = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            result[i] = this.components[i] + other.components[i];
        }
        return new Vector(result);
    }

    public Vector subtract(Vector other) throws VectorException {
        checkDimensions(other);
        double[] result = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            result[i] = this.components[i] - other.components[i];
        }
        return new Vector(result);
    }

    public double dotProduct(Vector other) throws VectorException {
        checkDimensions(other);
        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            sum += this.components[i] * other.components[i];
        }
        return sum;
    }

    public void display() {
        StringBuilder sb = new StringBuilder(dimension + "D Vector: (");
        for (int i = 0; i < dimension; i++) {
            sb.append(components[i]);
            if (i < dimension - 1) sb.append(", ");
        }
        sb.append(")");
        System.out.println(sb);
    }
}
