package co.bk.restspringboot;

public enum OrderState {

    INIT(4),
    PLACED(8),
    CANCELED(2048);
    
    private int state;
    
    /**
     * Enum constructors must be either private or package default, and protected or public access modifier is not allowed.
     * 
     * @param value
     */
    private OrderState(int state) {
        this.state = state;
    }
    
    public int getState() {
    	   return state;
    }
	
}
