package parallelAbelianSandpile;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Updater extends RecursiveTask {
    int[][]grid;
    int[][]updateGrid;
    int offset;
    int length;


    public Updater(int[][]current, int[][] next, int start, int length){
        offset=start;
        this.length=length;
        grid = current;
        updateGrid = next;

    }

    //for the next timestep - copy updateGrid into grid
	public void nextTimeStep() {
		for(int i=1; i<rows-1; i++ ) {
			for( int j=1; j<columns-1; j++ ) {
				this.grid[i][j]=updateGrid[i][j];
			}
		}
	}
        

    public Boolean compute(){
        if (length<=4){
            for( int i = offset; i<length; i++ ) {			//TODO easy to split amongst threads
                for( int j = offset; j<length; j++ ) {
                    updateGrid[i][j] = (grid[i][j] % 4) + 
                            (grid[i-1][j] / 4) +
                            (grid[i+1][j] / 4 )+
                            (grid[i][j-1] / 4 )+ 
                            (grid[i][j+1] / 4);
                }
                if (grid[i][j]!=updateGrid[i][j]) {  
					change=true;
				}
        }} //end nested for
        else {
            int split = (int)(length/2.0);

            Updater left = new Updater(updateGrid, grid, offset , split);
            Updater right = new Updater(updateGrid, grid,split+offset, length-split);
            left.fork();
            right.compute();
            left.join();
        }

        return change;

    }

    public static void main(String[] args) {
        System.out.println();
    }
}
