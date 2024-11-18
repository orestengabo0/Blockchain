//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int difficulty = 4;
        Blockchain blockchain = new Blockchain(difficulty);

        System.out.println("Mining block 1...");
        Block block1 = new Block("Block 1 data",blockchain.getChain().get(blockchain.getChain().size() - 1).getHash());
        blockchain.addBlock(block1);

        System.out.println("Mining block 2...");
        Block block2 = new Block("Block 2 data",blockchain.getChain().get(blockchain.getChain().size() - 1).getHash());
        blockchain.addBlock(block2);

        System.out.println("Blockchain: ");
        blockchain.printBlockChain();
        System.out.println("Is blockchain valid: "+blockchain.validateBlock());
    }
}