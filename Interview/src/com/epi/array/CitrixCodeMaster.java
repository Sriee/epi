package com.epi.array;

import java.util.*;

public class CitrixCodeMaster {
	
	private int[][] directions = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	
	private void priority() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		int category = 0, idx = 0, si = -1;
		int[] current = new int[3];
		int[] cat = new int[4];
		String[] prev = null;

		for(int i = 0; i < (n + 1); i++) {
			String[] item = scan.nextLine().split(" ");
			if(item.length < 4) continue;
			for(String token : item) {
				
				if(token.charAt(0)-'0' > si) si = token.charAt(0)-'0'; 
				switch(token.charAt(1)) {
				case 'F':
					cat[0] += 1;
					break;
				case 'P':
					cat[1] += 1;
					break;
				case 'U':
					cat[2] += 1;
					break;
				case 'S':
					cat[3] += 1;
					break;
				}
			}
			
			for(int j = 0; j < 4; j++) {
				if(cat[j] > category)
					category = cat[j];
			}
			
			if(category > current[0]) {
				prev = item;
				current[0] = category;
				current[1] = idx;
				current[2] = si;
			} else if (category == current[0] && si >= current[2]) {
				prev = item;
				current[1] = idx;
				current[2] = si;
			}
				
			Arrays.fill(cat, 0);
			si = -1;
			category  = 0;
			idx++;
		}
		System.out.println(prev);
		System.out.println((current[1] + 1));
		scan.close();
	}
	
	private void print(int[][] arr) {
		int row = arr.length, col = arr[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void serverGridFailure() {
		Scanner scan = new Scanner(System.in);
		String[] roco = scan.nextLine().split(" ");
		int row = Integer.parseInt(roco[0].trim());
		int col = Integer.parseInt(roco[1].trim());
		int[][] arr = new int[row][col];
		int x = -1, y = -1, max = 0;
		boolean all = true;
		
		while(scan.hasNextLine()) {
			String in = scan.nextLine();
			in = in.trim();
			if(in.isEmpty()) break;
			in = in.substring(1, in.length() - 1);
			x = Integer.parseInt(in.split(",")[0]) - 1;
			y = Integer.parseInt(in.split(",")[1]) - 1;
			
			arr[x][y] = -1;
			for(int[] direction : this.directions) {
				int nx = x + direction[0];
				int ny = y + direction[1];
				if(nx >=0 && ny >= 0 && nx < arr.length && ny < arr[0].length) {
					if(arr[nx][ny] == -1) continue;
					arr[nx][ny] += 1;
					if(arr[nx][ny] > max) max = arr[nx][ny];
				}
			}
		}
		
		this.print(arr);
		System.out.println();
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == -1) continue;
				if(arr[i][j] == max) {
					all = false;
					System.out.println(String.format("server [%d,%d]", i +1 , j + 1));
				}
			}
		}
		
		if(all) {
			System.out.println(String.format("SErver [%d,%d]", (arr.length/2) + 1 , (arr[0].length/2) + 1));
		}
		scan.close();

	}
	
	private void failingTests() {
		List<Double> min = new LinkedList<>();
		boolean inserted = false, init = false;		
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNextLine()) {
			
			if(!init) {
				for(int i = 0; i < 5; i++) 
					min.add(Double.MAX_VALUE);
				init = true;
			}
			double val = Double.parseDouble(scan.nextLine().trim());
			for(int j = 0; j < min.size() && !inserted; j++) {
				if(val < min.get(j)) {
					min.add(j, val);
					inserted = true;
				}
			}
			
			if(!inserted) min.add(val);
			if(min.size() > 5)
				min.remove(5);
			inserted = false;
		}
		
		for(Double val: min) 
			System.out.println(String.format("%.6f", val));
		scan.close();
	}
	
	/**
	 * 3 Hard Problems
	 * 
	 * 1. Find the priority
	 * 2. Server Grid Failure Problem 
	 * 3. Failing Tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CitrixCodeMaster ccm = new CitrixCodeMaster();
		ccm.priority();
		ccm.serverGridFailure();
		ccm.failingTests();
	}

}
