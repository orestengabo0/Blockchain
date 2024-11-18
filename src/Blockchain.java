import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
        chain.add(new Block("Genesis block", "0"));
    }
    public void addBlock(String data){
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(data, previousBlock.getPreviousHash());
        chain.add(newBlock);
    }
    public boolean validateBlock(){
        for(int i=0; i<chain.size(); i++){
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i-1);
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()))
                return false;
            if(!currentBlock.getPreviousHash().equals(previousBlock.getHash()))
                return false;
        }
        return true;
    }
    public void printBlockChain(){
        for(Block block : chain){
            System.out.println(block);
        }
    }
}
