import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private List<Block> blocks;

    public Wall(List<Block> allBlocks) {
        this.blocks = allBlocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                return extractedColor(color, block);
            }
            if (color.equals(block.getColor())) {
                return Optional.of(block);
            }

        }
        return Optional.empty();
    }

    private Optional<Block> extractedColor(String color, Block block) {
        if (block instanceof CompositeBlock) {
            extractedColor(color, block);
        }

        if (color.equals(block.getColor())) {
            return Optional.of(block);
        }

        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> finalListOfBlock = new ArrayList<>();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                List<Block> blocks = extractedMaterial(material, block);
                finalListOfBlock.addAll(blocks);
            } else if (material.equals(block.getMaterial())) {
                finalListOfBlock.add(block);
            }
        }
        return finalListOfBlock;
    }

    private List<Block> extractedMaterial(String material, Block block) {
        List<Block> result = new ArrayList<>();

        if (block instanceof CompositeBlock) {
            CompositeBlock compositeBlock = (CompositeBlock) block;
            for (Block subBlock : compositeBlock.getBlocks()) {
                result.addAll(extractedMaterial(material, subBlock));
            }
        } else if (material.equals(block.getMaterial())) {
            result.add(block);
        }

        return result;
    }

    @Override
    public int count() {
        int allCount = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {

                return countCompositeBlock(block, allCount);
            } else {
                allCount++;
            }
        }
        return allCount;
    }

    public int countCompositeBlock(Block block, int allCount) {
        allCount++;
        CompositeBlock compositeBlock = (CompositeBlock) block;
        for (Block subBlock : compositeBlock.getBlocks()) {
            if (subBlock instanceof CompositeBlock) {
                return countCompositeBlock(subBlock, allCount);
            } else {
                allCount++;
            }
        }
        return allCount;
    }

}
