
public class Ex2 {
	//Function 1# - color average that produces a gray image.
	public static int[][] rgb2gray(int[][][] im){
		int[][] grayImg = new int [im[0].length][im[0][0].length];
		for (int i = 0; i < grayImg.length; i++) {
			for (int j = 0; j < grayImg[i].length; j++) {
					grayImg[i][j] = (int) ((im[0][i][j]*0.3)+(im[1][i][j]*0.59)+(im[2][i][j]*0.11))*255;
			}
		}
		return grayImg;	
	}
	//Function 2# - clockwise rotation of an image.
	public static int[][][] rotate90(int[][][] im){
		int[][][] rightRot = new int [im.length][im[0][0].length][im[0].length];
		for (int i = 0; i < rightRot[0][0].length; i++) {
			for (int j = 0; j < rightRot[0].length; j++) {
				rightRot[0][j][rightRot[0][0].length-i-1]= im[0][i][j];
				rightRot[1][j][rightRot[0][0].length-i-1]= im[1][i][j];
				rightRot[2][j][rightRot[0][0].length-i-1]= im[2][i][j];
			}
		}
		return rightRot;	
	}
	//Function 3# - blurry image.
	public static int [][][] Smooth(int [][][] img,int n){
		int [][][] finSmooth = new int [img.length][img[0].length][img[0][0].length];
		for (int i = 0; i < img[0].length; i++) {
			for (int j = 0; j < img[0][0].length; j++) {
				finSmooth[0][i][j] = subValue (img, 0, i, j, n);
				finSmooth[1][i][j] = subValue (img, 1, i, j, n);
				finSmooth[2][i][j] = subValue (img, 2, i, j, n);
			}
		}
		return finSmooth;	
	}	
	//SubFunction 3A# - image square pixels average including corners.
	public static int subValue(int[][][] img, int rgb, int pixI, int pixJ, int n) {
	int pixel = 0, preSmooth = 0;
	for (int i =pixI-n; i<img[0].length && i<= pixI; i++) {
		if(i<0) {
			i=0;
		}
		for (int j = pixJ-n; j<img[0][0].length && j <= pixJ+n; j++) {
			if(j<0) {
				j=0;
			}
			preSmooth += img[rgb][i][j];
			pixel++;
		}
	}
		preSmooth /= pixel;
		return preSmooth;
	}
	//Function 4# - image stretching considering H&W input values based on nearest neighbor method.
	public static int[][] scaleup(double factor_h, double factor_w, int[][] im){
		int[][] stretchImg = new int [(int) (Math.round(im.length*factor_h))][(int) (Math.round(im[0].length*factor_w))];	
		for (int i = 0; i < im.length; i++) {
			for (int j = 0; j < im[0].length; j++) {
				for (int fh = 0; fh < factor_h; fh++) {	
					for (int fw = 0; fw < factor_w; fw++) {	
						 stretchImg[(int) (Math.floor((i*factor_h)+fh))][(int) (Math.floor((j*factor_w)+fw))] = im[i][j];
					}
				}
				
			}
		}
		return stretchImg;	
	}

	

	// "/Users/dorbenayoun/Downloads/Eiffel.jpg" - the location of the file you want to work on.
	public static void main(String[] args) {
		int [][][] image=MyImageIO.readImageFromFile("/Users/dorbenayoun/Downloads/Eiffel.jpg");
		MyImageIO.writeImageToFile("/Users/dorbenayoun/Downloads/EiffelGray", rgb2gray(image));
		MyImageIO.writeImageToFile("/Users/dorbenayoun/Downloads/EiffelClockwise", rotate90(image));
		MyImageIO.writeImageToFile("/Users/dorbenayoun/Downloads/EiffelBlurry", Smooth(image, 7));
		MyImageIO.writeImageToFile("/Users/dorbenayoun/Downloads/EiffelStretch", scaleup(2.0, 3.0, rgb2gray(image)));
	}
	
}