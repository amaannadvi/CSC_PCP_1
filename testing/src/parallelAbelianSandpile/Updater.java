//additions by S.A.Nadvi NDVSYE003
package parallelAbelianSandpile;
import java.util.concurrent.RecursiveTask;

public class Updater extends RecursiveTask<Boolean> {
    int[][]grid;
    int[][]updateGrid;
    int offsetX;
    int offsetY;
    int lengthX;
    int lengthY;
    int cutoffY;
    int cutoffX;



    public Updater(int[][]current, int[][] next, int startX,int startY, int lengthX,int lengthY,int cutoffX,int cutoffY){
        offsetX=startX;
        offsetY=startY;
        this.lengthX=lengthX;
        this.lengthY=lengthY;
        grid = current;
        updateGrid = next;
        this.cutoffX =cutoffX;
        this.cutoffY = cutoffY;
    }
    
    @Override
    protected Boolean compute(){
        boolean change= false;
        if (lengthX<=cutoffX && lengthY <= cutoffY){
            for( int i = offsetX; i<lengthX+offsetX; i++ ) {	
                for( int j = offsetY; j<lengthY+offsetY; j++ ) {
                    updateGrid[i][j] = (grid[i][j] % 4) + 
                            (grid[i-1][j] / 4) +
                            (grid[i+1][j] / 4 )+
                            (grid[i][j-1] / 4 )+ 
                            (grid[i][j+1] / 4);
                    if (grid[i][j]!=updateGrid[i][j]) {  
					    change = true;
                        
				    }

            }} //end nested for
        }
        else {
            int splitX = (int)(lengthX/2.0);
            int splitY = (int)(lengthY/2.0);
            int LLengthX = lengthX-splitX; //larger half of lengthX
            int LLengthY = lengthY - splitY;//larger half of lengthY
            
            boolean c1 = false;
            boolean c2 = false;
            boolean c3 = false;
            boolean c4 = false;
    
            
            Updater topLeft = new Updater(grid,updateGrid, offsetX,offsetY , splitX, splitY, cutoffX, cutoffY);
            Updater topRight = new Updater(grid,updateGrid,offsetX+splitX,offsetY , LLengthX,LLengthY, cutoffX, cutoffY);
            Updater bottomLeft = new Updater(grid,updateGrid,offsetX,offsetY+splitY, LLengthX,LLengthY, cutoffX, cutoffY);
            Updater bottomRight = new Updater(grid,updateGrid,offsetX+LLengthX,offsetY+LLengthY,splitX, splitY, cutoffX, cutoffY);

            topLeft.fork();
            topRight.fork();
            bottomLeft.fork();

            c1 = bottomRight.compute();
            c2 = topLeft.join();
            c3 = topRight.join();
            c4 = bottomLeft.join();
            //bottomRight.join();

            if (c1||c2||c3||c4){change=true;}
        }
        return change;
    }
}
