import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String scoreBoard[ ][ ]={{"3","3","4","","1",""},{"1","","2","4","3","4"},{"2","","4","3","4",""}};
		
		//System.out.println();
		boolean match = false; //using for calculate vector similarity
		// DO NOT run the following codes at one time!
		//u2u(scoreBoard, 0, match); //Question 1
		//u2u(scoreBoard, 1, match);
		//i2i(scoreBoard, 0, match); //Question 2
		i2i(scoreBoard, 1, match); //Question 3
		
		
	}
	
	public static double u2uAverage(String[] score, int id){
		double length = 0;
		double sum = 0;
		boolean flag = true;
		System.out.print("Mean("+id+")=(");
		for (int i = 0; i < score.length; i++){
			if (!score[i].equals("")){
				if(flag){
					System.out.print(score[i]);
				} else {
					System.out.print("+"+score[i]);
				}
				sum = sum + Double.parseDouble(score[i]);
				length++;
				flag = false;
			}
		}
		System.out.print(")/"+length+"="+sum/length);
		System.out.println();
		return sum/length;
	}
	
	public static double i2iAverage(String[][]scoreBoard, int id){
		double length = 0;
		double sum = 0;
		boolean flag = true;
		System.out.print("Mean("+id+")=(");
		for (int i = 0; i < scoreBoard.length; i++){
			if (!scoreBoard[i][id].equals("")){
				if(flag){
					System.out.print(scoreBoard[i][id]);
				} else {
					System.out.print("+"+scoreBoard[i][id]);
				}
				sum = sum + Double.parseDouble(scoreBoard[i][id]);
				length++;
				flag = false;
			}
		}
		System.out.print(")/"+length+"="+sum/length);
		System.out.println();
		return sum/length;
	}
	
	public static double[][] pearson(int length, int width, String[][] scoreBoard, double avg[], int mode){
		double simTable[][] = new double[length][length];
		double first = 0;
		double second = 0;
		double third = 0;
		for (int i = 0; i < length; i++){
			for (int j = 0; j < length; j++){
				first = 0;
				second = 0;
				third = 0;
				if(j>=i){
					
					for(int m = 0; m < width; m++) {
						if (mode == 0) { //u2u
							if(!scoreBoard[i][m].equals("")&&!scoreBoard[j][m].equals("")){
								//System.out.println("i="+i+",j="+j+",first="+scoreBoard[j][m]+" - "+avg[j]);
								first = first + (Double.parseDouble(scoreBoard[i][m]) - avg[i]) * (Double.parseDouble(scoreBoard[j][m]) - avg[j]);
								second = second + Math.pow((Double.parseDouble(scoreBoard[i][m]) - avg[i]),2);
								third = third + Math.pow((Double.parseDouble(scoreBoard[j][m]) - avg[j]),2);
							}
						} else { //i2i
							if(!scoreBoard[m][i].equals("")&&!scoreBoard[m][j].equals("")){
								//System.out.println("i="+i+",j="+j+",first="+scoreBoard[j][m]+" - "+avg[j]);
								first = first + (Double.parseDouble(scoreBoard[m][i]) - avg[i]) * (Double.parseDouble(scoreBoard[m][j]) - avg[j]);
								second = second + Math.pow((Double.parseDouble(scoreBoard[m][i]) - avg[i]),2);
								third = third + Math.pow((Double.parseDouble(scoreBoard[m][j]) - avg[j]),2);
							}
						}
							
						//System.out.println("i="+i+",j="+j+",first="+first+",second="+second+",third="+third);
					}
					if(Math.sqrt(second*third)!=0){
						simTable[i][j] = first/Math.sqrt(second*third);
						simTable[j][i] = first/Math.sqrt(second*third);
					} else {
						simTable[i][j] = 0.0;
						simTable[j][i] = 0.0;
					}
					
				}
			}
		}
		return simTable;
	}
	
	
	public static double[][] vector(int length, int width, String[][] scoreBoard, double avg[], int mode, boolean match){
		double simTable[][] = new double[length][length];
		double first = 0;
		double second = 0;
		double third = 0;
		for (int i = 0; i < length; i++){
			for (int j = 0; j < length; j++){
				first = 0;
				second = 0;
				third = 0;
				if(j>=i){
					
					for(int m = 0; m < width; m++) {
						if (mode == 0) { //u2u
							if(match){
								//System.out.println("i="+i+",j="+j+",first="+scoreBoard[j][m]+" - "+avg[j]);
								first = first + Double.parseDouble(scoreBoard[i][m]) * Double.parseDouble(scoreBoard[j][m]);
								second = second + Math.pow(Double.parseDouble(scoreBoard[i][m]),2);
								third = third + Math.pow(Double.parseDouble(scoreBoard[j][m]),2);
							} else {
								double a = 0.0;
								double b = 0.0;
								if(!scoreBoard[i][m].equals("")){
									a= Double.parseDouble(scoreBoard[i][m]);
								}
								if(!scoreBoard[j][m].equals("")){
									b = Double.parseDouble(scoreBoard[j][m]);
								}
								first = first + a * b;
								second = second + Math.pow(a, 2);
								third = third + Math.pow(b, 2);
								
							}
						} else { //i2i
							if(match){
								//System.out.println("i="+i+",j="+j+",first="+scoreBoard[j][m]+" - "+avg[j]);
								first = first + Double.parseDouble(scoreBoard[m][i]) * Double.parseDouble(scoreBoard[m][j]);
								second = second + Math.pow(Double.parseDouble(scoreBoard[m][i]),2);
								third = third + Math.pow(Double.parseDouble(scoreBoard[m][j]),2);
							} else {
								double a = 0.0;
								double b = 0.0;
								if(!scoreBoard[m][i].equals("")){
									a= Double.parseDouble(scoreBoard[m][i]);
								}
								if(!scoreBoard[m][j].equals("")){
									b = Double.parseDouble(scoreBoard[m][j]);
								}
								first = first + a * b;
								second = second + Math.pow(a, 2);
								third = third + Math.pow(b, 2);
							}
						}
							
						
					}
					if(Math.sqrt(second*third)!=0){
						simTable[i][j] = first/Math.sqrt(second*third);
						simTable[j][i] = first/Math.sqrt(second*third);
					} else {
						simTable[i][j] = 0.0;
						simTable[j][i] = 0.0;
					}
					
					//System.out.println("i="+i+",j="+j+",first="+first+",second="+second+",third="+third);
				
				}
			}
		}
		return simTable;
	}
	
	
	public static String[][] u2up(int length, int width, String[][] scoreBoard, double avg[], double[][] simTable){
		String[][] newScoreBoard = scoreBoard.clone();
		double first = 0.0;
		double second = 0.0;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++){
				if(scoreBoard[i][j].equals("")){
					first = 0.0;
					second = 0.0;
					for (int m = 0; m < length; m++){
						if (m != i && !scoreBoard[m][j].equals("")){
							first = first + simTable[i][m] * (Double.parseDouble(scoreBoard[m][j])-avg[m]);
							second = second + Math.abs(simTable[i][m]);
						}
					}
					newScoreBoard[i][j] = avg[i] + first / second + "";
					//System.out.println("i="+i+",j="+j+",first="+first+",second="+second+",avg="+avg[i]);
					
				}
			}
		}
		return newScoreBoard;
	}
	
	public static String[][] i2ip(int length, int width, String[][] scoreBoard, double avg[], double[][] simTable){
		String[][] newScoreBoard = scoreBoard.clone();
		double first = 0.0;
		double second = 0.0;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++){
				if(scoreBoard[i][j].equals("")){
					first = 0.0;
					second = 0.0;
					for (int m = 0; m < width; m++){
						if (m != j && !scoreBoard[i][m].equals("")){
							first = first + simTable[j][m] * Double.parseDouble(scoreBoard[i][m]);
							second = second + Math.abs(simTable[j][m]);
						}
					}
					if(second != 0){
						newScoreBoard[i][j] = first / second + "";
					} else {
						newScoreBoard[i][j] = "0";
					}
					
					//System.out.println("i="+i+",j="+j+",first="+first+",second="+second+",avg="+avg[i]);
					
				}
			}
		}
		return newScoreBoard;
	}
	
	public static void u2u (String[][] scoreBoard, int mode, boolean match) {
		int length = scoreBoard.length;
		int width = scoreBoard[0].length;
		double avg[] = new double[length];
		System.out.println("Calculate average first");
		for (int i = 0; i < length; i++){
			avg[i] = u2uAverage(scoreBoard[i], i);
		}
		double simTable[][];
		if(mode == 0){
			simTable = pearson(length, width, scoreBoard, avg, 0);
		} else {
			simTable = vector(length, width, scoreBoard, avg, 0, match);
		}
		System.out.println("Calculate similarity for each pair of user");
		System.out.println(Arrays.deepToString(simTable));
		//scoreBoard = u2up(length, width, scoreBoard, avg, simTable);
		System.out.println("Calculate each blank");
		System.out.println(Arrays.deepToString(u2up(length, width, scoreBoard, avg, simTable)));
		//System.out.println(scoreBoard[1][1]);
	}
	
	public static void i2i (String[][] scoreBoard, int mode, boolean match) {
		int length = scoreBoard.length;
		int width = scoreBoard[0].length;
		double avg[] = new double[width];
		System.out.println("Calculate average first");
		for (int i = 0; i < width; i++){
			avg[i] = i2iAverage(scoreBoard, i);
		}
		//System.out.println(avg[0]);
		double simTable[][];
		if (mode == 0){
			simTable = pearson(width, length, scoreBoard, avg, 1);
		} else {
			simTable = vector(width, length, scoreBoard, avg, 1, match);
		}
		
		
		System.out.println("Calculate similarity for each pair of user");
		System.out.println(Arrays.deepToString(simTable));
		//scoreBoard = i2ip(length, width, scoreBoard, avg, simTable);
		System.out.println("Calculate each blank");
		System.out.println(Arrays.deepToString(i2ip(length, width, scoreBoard, avg, simTable)));
		//System.out.println(scoreBoard[1][1]);
		
	}
	

}
