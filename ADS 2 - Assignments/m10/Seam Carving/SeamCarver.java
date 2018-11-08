import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	Picture picture;
	public SeamCarver(Picture picture) {
		this.picture = picture;
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || x == picture.width() - 1 || y == picture.height() - 1) {
			return 1000;
		} else {
			Color top = picture.get(x - 1, y);
			Color bottom = picture.get(x + 1, y);
			Color left = picture.get(x, y - 1);
			Color right = picture.get(x, y + 1);
			double delta_x = (Math.pow((right.getRed() - left.getRed()), 2) + Math.pow((right.getGreen() - left.getGreen()), 2) + Math.pow((right.getBlue() - left.getBlue()), 2));
			double delta_y = (Math.pow((bottom.getRed() - top.getRed()), 2) + Math.pow((bottom.getGreen() - top.getGreen()), 2) + Math.pow((bottom.getBlue() - top.getBlue()), 2));
			double energy = Math.sqrt(delta_x + delta_y);
			return energy;
		}
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}