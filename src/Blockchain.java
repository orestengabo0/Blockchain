import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> chain;
    private int difficulty;

    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        Block genesisBlock = new Block("Genesis Block","0");
        genesisBlock.mineBlock(difficulty);
        chain.add(genesisBlock);
    }
    public void addBlock(Block newBlock){
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }
    public boolean validateBlock(){
        Block genesisBlock = chain.get(0);
        if(!genesisBlock.getHash().equals(genesisBlock.calculateHash())){
            return false;
        }
        for(int i=1; i<chain.size(); i++){
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
    public ArrayList<Block> getChain() {
        return chain;
    }
}
