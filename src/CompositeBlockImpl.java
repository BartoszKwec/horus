import java.util.List;

public class CompositeBlockImpl implements CompositeBlock{


    private List<Block> block;


    public CompositeBlockImpl(List<Block> blocksToComposite) {
        this.block = blocksToComposite;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }

    @Override
    public List<Block> getBlocks() {
        return block;
    }
}
