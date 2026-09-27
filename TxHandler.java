import java.util.ArrayList;
import java.util.HashSet;

public class TxHandler {

    private UTXOPool utxoPool;
	/* Creates a public ledger whose current UTXOPool (collection of unspent 
	 * transaction outputs) is utxoPool. This should make a defensive copy of 
	 * utxoPool by using the UTXOPool(UTXOPool uPool) constructor.
	 */
	public TxHandler(UTXOPool utxoPool) {
		// IMPLEMENT THIS
        this.utxoPool = new UTXOPool(utxoPool);
	}

	/* Returns true if 
	 * (1) all outputs claimed by tx are in the current UTXO pool, 
	 * (2) the signatures on each input of tx are valid, 
	 * (3) no UTXO is claimed multiple times by tx, 
	 * (4) all of tx’s output values are non-negative, and
	 * (5) the sum of tx’s input values is greater than or equal to the sum of   
	        its output values;
	   and false otherwise.
	 */

	public boolean isValidTx(Transaction tx) {
		// IMPLEMENT THIS
        if (tx == null) {
		return false;
	}

    HashSet<UTXO> claimed = new HashSet<UTXO>();
    double inputSum = 0;
    double outputSum = 0;

    for (int i = 0; i < tx.numInputs(); i++) {
        Transaction.Input input = tx.getInput(i);

        // Preliminary check for tx hash and signature 
        if (input.prevTxHash == null || input.signature == null) {
            return false;
        }

        UTXO spent = new UTXO(input.prevTxHash, input.outputIndex);
        // Check for Rule 1 and Rule 3 
        if (!utxoPool.contains(spent) || !claimed.add(spent)) {
            return false;
        }

        // Retrieve old tx output that current input claims to spend
        Transaction.Output previous = utxoPool.getTxOutput(spent);

    
        // Check for Rule 2 
        if (previous == null || previous.address == null ||
                !previous.address.verifySignature(
                    tx.getRawDataToSign(i), input.signature)) {
            return false;
        }
        
        // Check for Rule 5 
        inputSum += previous.value;
    }
    
    return false;
}
    // Check every new output tx wants to create
    for (Transaction.Output output : tx.getOutputs()) {
        // Check for Rule 4
        if (output == null || !(output.value >0)) {
            return false;
        }

        // Rule 5 
        outputSum += output.value;
    }
        // Output can't surpass input, no spending nonexistent funds
        return inputSum >= outputSum;
    }

	/* Handles each epoch by receiving an unordered array of proposed 
	 * transactions, checking each transaction for correctness, 
	 * returning a mutually valid array of accepted transactions, 
	 * and updating the current UTXO pool as appropriate.
	 */
	public Transaction[] handleTxs(Transaction[] possibleTxs) {
		// IMPLEMENT THIS
		return null;
	}
 

