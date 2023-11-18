import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        final List<Block> simpleBlocks = generateSimpleBlocks();
        final List<Block> allBlocks = new ArrayList<>(simpleBlocks);
        final CompositeBlockImpl compositeBlock = generateCompositeBlock();
        allBlocks.add(compositeBlock);

        final Wall wall = new Wall(allBlocks);
        final Optional<Block> blockByColor = wall.findBlockByColor("red");
        final List<Block> blocksByMaterial = wall.findBlocksByMaterial("wood");

        printResults(blockByColor, blocksByMaterial, wall);

    }

    private static void printResults(final Optional<Block> blockByColor, final List<Block> blocksByMaterial,
                                     final Wall wall) {
        blockByColor.ifPresent(block -> System.out.println(block.getColor() + " " + block.getMaterial()));
        System.out.println("---------------------------------------------------------");
        blocksByMaterial.forEach(block -> System.out.println(block.getMaterial() + " " + block.getColor()));
        System.out.println("---------------------------------------------------------");

        System.out.println("wall count: " + wall.count());
    }

    private static List<Block> generateSimpleBlocks() {
        return List.of(
                new BlockImpl("red", "wood"),
                new BlockImpl("white", "wood"),
                new BlockImpl("black", "wood"),
                new BlockImpl("black", "stone")
        );
    }

    private static CompositeBlockImpl generateCompositeBlock() {
        List<Block> blocksToComposite = List.of(
                new BlockImpl("white", "stone"),
                new BlockImpl("white", "stone"),
                new BlockImpl("black", "stone"),
                new BlockImpl("red", "stone"),
                new CompositeBlockImpl(List.of(new BlockImpl("pink", "stone"), new BlockImpl("pink", "wood")))
        );
        return new CompositeBlockImpl(blocksToComposite);
    }

}