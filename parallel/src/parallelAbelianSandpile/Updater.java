//additions by S.A.Nadvi NDVSYE003
package parallelAbelianSandpile;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Updater extends RecursiveTask {
    int[][]grid;
    int[][]updateGrid;
    int offsetX;
    int offsetY;
    int lengthX;
    int lengthY;



    public Updater(int[][]current, int[][] next, int startX,int startY, int lengthX,int lengthY){
        offsetX=startX;
        offsetY=startY;
        this.lengthX=lengthX;
        this.lengthY=lengthY;
        grid = current;
        updateGrid = next;

    }
    
    @Override
    protected Boolean compute(){
        
        if (lengthX<=64){
            for( int i = offsetX; i<lengthX+offsetX; i++ ) {	
                for( int j = offsetY; j<lengthY+offsetY; j++ ) {
                    updateGrid[i][j] = (grid[i][j] % 4) + 
                            (grid[i-1][j] / 4) +
                            (grid[i+1][j] / 4 )+
                            (grid[i][j-1] / 4 )+ 
                            (grid[i][j+1] / 4);
                    if (grid[i][j]!=updateGrid[i][j]) {  
					    AutomatonSimulation.simulationGrid.setChange(true);
                        
				    }
            }} //end nested for
        }
        else {
            int splitX = (int)(lengthX/2.0);
            int splitY = (int)(lengthY/2.0);
            int LLengthX = lengthX-splitX; //larger half of lengthX
            int LLengthY = lengthY - splitY;//larger half of lengthY
            
            Updater topLeft = new Updater(grid,updateGrid, offsetX,offsetY , splitX, splitY);
            Updater topRight = new Updater(grid,updateGrid,offsetX+splitX,offsetY , LLengthX,LLengthY);
            Updater bottomLeft = new Updater(grid,updateGrid,offsetX,offsetY+splitY, LLengthX,LLengthY);
            Updater bottomRight = new Updater(grid,updateGrid,offsetX+LLengthX,offsetY+LLengthY,splitX, splitY);

            topLeft.fork();
            topRight.fork();
            bottomLeft.fork();
            bottomRight.compute();
            topLeft.join();
            topRight.join();
            bottomLeft.join();
            //bottomRight.join();
        }
        return AutomatonSimulation.simulationGrid.getChange();
    }
}
