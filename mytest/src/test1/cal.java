package test1;

public class cal {
    private boolean[] arr=new boolean[100];
    private int[] money={1,1,1,5,5,20,50};
    public boolean Triangle(int x){
    	if(x > 83 || x < 0)
    		return false;
    	init();
    	arr[0]=true;
    	for(int i = 0; i < money.length; i++){
    		
    	    for(int j = 100; j > 0; j--){
    	    	if(j-money[i]>=0 && arr[j-money[i]]==true)
    	    		arr[j]=true;
    	    }
    	    
    	}
        if(arr[x]==true)
        {
        	return true;
        }
        return false;
    }
    private void init(){
    	for(int i = 0; i < 100; i++){
    		arr[i]=false;
    	}
    }
}
