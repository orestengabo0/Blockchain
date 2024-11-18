import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String data;
    private String hash;
    private String previousHash;
    private long timestamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.hash = calculateHash();
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
    }

    public String calculateHash(){
        String input = data + previousHash + Long.toString(timestamp) + Integer.toString(nonce);
        return applySha256(input);
    }

    public String applySha256(String input){
        MessageDigest digest = null;
        byte[] bytes= null;
        try{
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for(byte b : bytes){
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String mineBlock(int prefix){
        String prefixString = new String(new char[prefix]).replace('\0','0');
        while(!hash.substring(0, prefix).equals(prefixString)){
            nonce++;
            hash = calculateHash();
        }
        return hash;
    }

    public String getData() {
        return data;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }
}
