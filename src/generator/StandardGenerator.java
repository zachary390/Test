package generator;
import java.util.ArrayList;
import multiblock.Block;
import multiblock.Multiblock;
import multiblock.action.PostProcessingAction;
import multiblock.action.SetblockAction;
import multiblock.action.SymmetryAction;
import multiblock.overhaul.fissionmsr.OverhaulMSR;
import multiblock.overhaul.fissionsfr.OverhaulSFR;
import multiblock.ppe.PostProcessingEffect;
import multiblock.symmetry.Symmetry;
import multiblock.underhaul.fissionsfr.UnderhaulSFR;
import planner.Core;
import planner.menu.component.MenuComponentLabel;
import planner.menu.component.MenuComponentMinimaList;
import planner.menu.component.MenuComponentMinimalistButton;
import planner.menu.component.MenuComponentMinimalistTextBox;
import planner.menu.component.MenuComponentPostProcessingEffect;
import planner.menu.component.MenuComponentPriority;
import planner.menu.component.MenuComponentSymmetry;
import planner.menu.component.MenuComponentToggle;
import simplelibrary.opengl.gui.components.MenuComponent;
public class StandardGenerator extends MultiblockGenerator{
//    private MenuComponentMinimalistTextBox finalMultiblockCount;
    private MenuComponentMinimalistTextBox workingMultiblockCount;
    private MenuComponentMinimalistTextBox timeout;
    private MenuComponentMinimaList prioritiesList;
    private MenuComponentMinimalistButton moveUp;
    private MenuComponentMinimalistButton moveDown;
    private MenuComponentMinimaList symmetriesList;
    private MenuComponentMinimaList postProcessingEffectsList;
    private MenuComponentMinimalistTextBox changeChance;
    private MenuComponentToggle variableRate;
    private MenuComponentToggle lockCore;
    private MenuComponentToggle fillAir;
    private GeneratorSettings settings = new GeneratorSettings();
    private final ArrayList<Multiblock> finalMultiblocks = new ArrayList<>();
    private final ArrayList<Multiblock> workingMultiblocks = new ArrayList<>();
    private int index = 0;
    public StandardGenerator(Multiblock multiblock){
        super(multiblock);
    }
    @Override
    public ArrayList<Multiblock>[] getMultiblockLists(){
        return new ArrayList[]{(ArrayList)finalMultiblocks.clone(),(ArrayList)workingMultiblocks.clone()};
    }
    @Override
    public Multiblock[] getValidMultiblocks(){
        return Core.multiblockTypes.toArray(new Multiblock[Core.multiblockTypes.size()]);
    }
    @Override
    public String getName(){
        return "Standard";
    }
    @Override
    public void addSettings(MenuComponentMinimaList generatorSettings, Multiblock multi){
//        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Final Multiblocks", true));
//        finalMultiblockCount = generatorSettings.add(new MenuComponentMinimalistTextBox(0, 0, 0, 32, "2", true).setIntFilter());
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Working Multiblocks", true));
        workingMultiblockCount = generatorSettings.add(new MenuComponentMinimalistTextBox(0, 0, 0, 32, "6", true).setIntFilter());
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Timeout (sec)", true));
        timeout = generatorSettings.add(new MenuComponentMinimalistTextBox(0, 0, 0, 32, "10", true).setIntFilter());
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Priorities", true));
        prioritiesList = generatorSettings.add(new MenuComponentMinimaList(0, 0, 0, priorities.size()*32, 24){
            @Override
            public void render(int millisSinceLastTick){
                for(MenuComponent c : components){
                    c.width = width-(hasVertScrollbar()?vertScrollbarWidth:0);
                }
                super.render(millisSinceLastTick);
            }
        });
        refreshPriorities();
        MenuComponent priorityButtonHolder = generatorSettings.add(new MenuComponent(0, 0, 0, 32){
            @Override
            public void renderBackground(){
                components.get(1).x = width/2;
                components.get(0).width = components.get(1).width = width/2;
                components.get(0).height = components.get(1).height = height;
            }
            @Override
            public void render(){}
        });
        moveUp = priorityButtonHolder.add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Move Up", true, true));
        moveUp.addActionListener((e) -> {
            int index = prioritiesList.getSelectedIndex();
            if(index==-1||index==0)return;
            priorities.add(index-1, priorities.remove(index));
            refreshPriorities();
            prioritiesList.setSelectedIndex(index-1);
        });
        moveDown = priorityButtonHolder.add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Move Down", true, true));
        moveDown.addActionListener((e) -> {
            int index = prioritiesList.getSelectedIndex();
            if(index==-1||index==priorities.size()-1)return;
            priorities.add(index+1, priorities.remove(index));
            refreshPriorities();
            prioritiesList.setSelectedIndex(index+1);
        });
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Generator Settings", true));
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 24, "Change Chance", true));
        changeChance = generatorSettings.add(new MenuComponentMinimalistTextBox(0, 0, 0, 32, "1", true).setFloatFilter(0f, 100f).setSuffix("%"));
        variableRate = generatorSettings.add(new MenuComponentToggle(0, 0, 0, 32, "Variable Rate", true));
        lockCore = generatorSettings.add(new MenuComponentToggle(0, 0, 0, 32, "Lock Core", false));
        fillAir = generatorSettings.add(new MenuComponentToggle(0, 0, 0, 32, "Fill Air", false));
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Symmetry Settings", true));
        ArrayList<Symmetry> symmetries = multi.getSymmetries();
        symmetriesList = generatorSettings.add(new MenuComponentMinimaList(0, 0, 0, symmetries.size()*32, 24));
        for(Symmetry symmetry : symmetries){
            symmetriesList.add(new MenuComponentSymmetry(symmetry));
        }
        generatorSettings.add(new MenuComponentLabel(0, 0, 0, 32, "Post-Processing", true));
        ArrayList<PostProcessingEffect> postProcessingEffects = multi.getPostProcessingEffects();
        postProcessingEffectsList = generatorSettings.add(new MenuComponentMinimaList(0, 0, 0, postProcessingEffects.size()*32, 24));
        for(PostProcessingEffect postProcessingEffect : postProcessingEffects){
            postProcessingEffectsList.add(new MenuComponentPostProcessingEffect(postProcessingEffect));
        }
    }
    @Override
    public MultiblockGenerator newInstance(Multiblock multi){
        return new StandardGenerator(multi);
    }
    private void refreshPriorities(){
        prioritiesList.components.clear();
        for(Priority priority : priorities){
            prioritiesList.add(new MenuComponentPriority(priority));
        }
    }
    @Override
    public void refreshSettings(ArrayList<Block> allowedBlocks){
        settings.refresh(allowedBlocks);
    }
    @Override
    public void tick(){
        int size;
        //<editor-fold defaultstate="collapsed" desc="Adding/removing working multiblocks">
        synchronized(workingMultiblocks){
            size = workingMultiblocks.size();
        }
        if(size<settings.workingMultiblocks){
            Multiblock inst = multiblock.blankCopy();
            synchronized(workingMultiblocks){
                workingMultiblocks.add(inst);
            }
        }else if(size>settings.workingMultiblocks){
            synchronized(workingMultiblocks){
                Multiblock worst = null;
                for(Multiblock mb : workingMultiblocks){
                    if(worst==null||worst.isBetterThan(mb, settings.priorities)){
                        worst = mb;
                    }
                }
                if(worst!=null){
                    finalize(worst);
                    workingMultiblocks.remove(worst);
                }
            }
        }
//</editor-fold>
        Multiblock currentMultiblock = null;
        int idx = index;
        //<editor-fold defaultstate="collapsed" desc="Fetch Current multiblock">
        synchronized(workingMultiblocks){
            if(idx>=workingMultiblocks.size())idx = 0;
            if(!workingMultiblocks.isEmpty()){
                currentMultiblock = workingMultiblocks.get(idx).copy();
            }
            index++;
            if(index>=workingMultiblocks.size())index = 0;
        }
//</editor-fold>
        if(currentMultiblock==null)return;//there's nothing to do!
        if(settings.variableRate){
            for(int x = 0; x<currentMultiblock.getX(); x++){
                for(int y = 0; y<currentMultiblock.getY(); y++){
                    for(int z = 0; z<currentMultiblock.getZ(); z++){
                        Block b = currentMultiblock.getBlock(x, y, z);
                        if(settings.lockCore&&b!=null&&b.isCore())continue;
                        if(rand.nextDouble()<settings.getChangeChance()||(settings.fillAir&&b==null)){
                            Block randBlock = settings.allowedBlocks.get(rand.nextInt(settings.allowedBlocks.size()));
                            if(settings.lockCore&&randBlock.isCore())continue;//nope
                            currentMultiblock.queueAction(new SetblockAction(x, y, z, applyMultiblockSpecificSettings(randBlock.newInstance(x, y, z))));
                        }
                    }
                }
            }
        }else{
            int changes = (int) Math.max(1, Math.round(settings.changeChancePercent*currentMultiblock.getVolume()));
            ArrayList<int[]> pool = new ArrayList<>();
            for(int X = 0; X<currentMultiblock.getX(); X++){
                for(int Y = 0; Y<currentMultiblock.getY(); Y++){
                    for(int Z = 0; Z<currentMultiblock.getZ(); Z++){
                        if(settings.fillAir&&currentMultiblock.getBlock(X, Y, Z)==null){
                            Block randBlock = settings.allowedBlocks.get(rand.nextInt(settings.allowedBlocks.size()));
                            if(settings.lockCore&&randBlock.isCore())continue;//nope
                            currentMultiblock.queueAction(new SetblockAction(X, Y, Z, applyMultiblockSpecificSettings(randBlock.newInstance(X, Y, Z))));
                            continue;
                        }
                        pool.add(new int[]{X,Y,Z});
                    }
                }
            }
            for(int i = 0; i<changes; i++){//so it can't change the same cell twice
                if(pool.isEmpty())break;
                int[] pos = pool.remove(rand.nextInt(pool.size()));
                Block b = currentMultiblock.getBlock(pos[0], pos[1], pos[2]);
                if(settings.lockCore&&b!=null&&b.isCore())continue;
                Block randBlock = settings.allowedBlocks.get(rand.nextInt(settings.allowedBlocks.size()));
                if(settings.lockCore&&randBlock.isCore())continue;//nope
                currentMultiblock.queueAction(new SetblockAction(pos[0], pos[1], pos[2], applyMultiblockSpecificSettings(randBlock.newInstance(pos[0], pos[1], pos[2]))));
            }
        }
        currentMultiblock.performActions();
        for(PostProcessingEffect effect : settings.postProcessingEffects){
            if(effect.preSymmetry)currentMultiblock.action(new PostProcessingAction(effect));
        }
        for(Symmetry symmetry : settings.symmetries){
            currentMultiblock.queueAction(new SymmetryAction(symmetry));
        }
        currentMultiblock.performActions();
        currentMultiblock.recalculate();
        for(PostProcessingEffect effect : settings.postProcessingEffects){
            if(effect.postSymmetry)currentMultiblock.action(new PostProcessingAction(effect));
        }
        synchronized(workingMultiblocks.get(idx)){
            Multiblock mult = workingMultiblocks.get(idx);
            finalize(mult);
            if(currentMultiblock.isBetterThan(mult, settings.priorities)){workingMultiblocks.set(idx, currentMultiblock.copy());}
            else if(mult.millisSinceLastChange()>settings.timeout*1000){
                workingMultiblocks.set(idx, multiblock.blankCopy());
            }
        }
    }
    private Block applyMultiblockSpecificSettings(Block randBlock){
        if(multiblock instanceof UnderhaulSFR)return randBlock;//no block-specifics here!
        if(multiblock instanceof OverhaulSFR){
            multiblock.overhaul.fissionsfr.Block block = (multiblock.overhaul.fissionsfr.Block)randBlock;
            if(block.isFuelCell()){
                ArrayList<multiblock.configuration.overhaul.fissionsfr.Fuel> allowedFuels = ((OverhaulSFR)multiblock).getValidFuels();
                if(allowedFuels.isEmpty())return null;
                block.fuel = allowedFuels.get(rand.nextInt(allowedFuels.size()));
                ArrayList<multiblock.configuration.overhaul.fissionsfr.Source> allowedSources = ((OverhaulSFR)multiblock).getValidSources();
                if(!allowedSources.isEmpty())block.source = allowedSources.get(rand.nextInt(allowedSources.size()));
            }
            if(block.isIrradiator()){
                ArrayList<multiblock.configuration.overhaul.fissionsfr.IrradiatorRecipe> allowedIrradiatorRecipes = ((OverhaulSFR)multiblock).getValidIrradiatorRecipes();
                if(!allowedIrradiatorRecipes.isEmpty())block.irradiatorRecipe = allowedIrradiatorRecipes.get(rand.nextInt(allowedIrradiatorRecipes.size()));
            }
            return randBlock;
        }
        if(multiblock instanceof OverhaulMSR){
            multiblock.overhaul.fissionmsr.Block block = (multiblock.overhaul.fissionmsr.Block)randBlock;
            if(block.isFuelVessel()){
                ArrayList<multiblock.configuration.overhaul.fissionmsr.Fuel> allowedFuels = ((OverhaulMSR)multiblock).getValidFuels();
                if(allowedFuels.isEmpty())return null;
                block.fuel = allowedFuels.get(rand.nextInt(allowedFuels.size()));
                ArrayList<multiblock.configuration.overhaul.fissionmsr.Source> allowedSources = ((OverhaulMSR)multiblock).getValidSources();
                if(!allowedSources.isEmpty())block.source = allowedSources.get(rand.nextInt(allowedSources.size()));
            }
            if(block.isIrradiator()){
                ArrayList<multiblock.configuration.overhaul.fissionmsr.IrradiatorRecipe> allowedIrradiatorRecipes = ((OverhaulMSR)multiblock).getValidIrradiatorRecipes();
                if(!allowedIrradiatorRecipes.isEmpty())block.irradiatorRecipe = allowedIrradiatorRecipes.get(rand.nextInt(allowedIrradiatorRecipes.size()));
            }
            return randBlock;
        }
        throw new IllegalArgumentException("Unknown multiblock: "+multiblock.getDefinitionName());
    }
    private void finalize(Multiblock worst){
        if(worst==null)return;
        synchronized(finalMultiblocks){
        //<editor-fold defaultstate="collapsed" desc="Adding/removing final multiblocks">
            if(finalMultiblocks.size()<settings.finalMultiblocks){
                finalMultiblocks.add(worst.copy());
                return;
            }else if(finalMultiblocks.size()>settings.finalMultiblocks){
                Multiblock wrst = null;
                for(Multiblock mb : finalMultiblocks){
                    if(wrst==null||wrst.isBetterThan(mb, settings.priorities)){
                        wrst = mb;
                    }
                }
                if(wrst!=null){
                    finalMultiblocks.remove(wrst);
                }
            }
//</editor-fold>
            for(int i = 0; i<finalMultiblocks.size(); i++){
                Multiblock multi = finalMultiblocks.get(i);
                if(worst.isBetterThan(multi, settings.priorities)){
                    finalMultiblocks.set(i, worst.copy());
                    return;
                }
            }
        }
    }
    @Override
    public void importMultiblock(Multiblock<Block> multiblock){
        finalize(multiblock);
    }
    private class GeneratorSettings{
        public int finalMultiblocks, workingMultiblocks, timeout;
        public ArrayList<Priority> priorities = new ArrayList<>();
        public ArrayList<Symmetry> symmetries = new ArrayList<>();
        public ArrayList<PostProcessingEffect> postProcessingEffects = new ArrayList<>();
        public ArrayList<Block> allowedBlocks = new ArrayList<>();
        public float changeChancePercent;
        public boolean variableRate, lockCore, fillAir;
        public void refresh(ArrayList<Block> allowedBlocks){
            this.allowedBlocks = allowedBlocks;
            finalMultiblocks = 1;//Integer.parseInt(StandardGenerator.this.finalMultiblockCount.text);
            workingMultiblocks = Integer.parseInt(StandardGenerator.this.workingMultiblockCount.text);
            timeout = Integer.parseInt(StandardGenerator.this.timeout.text);
            ArrayList<Symmetry> newSymmetries = new ArrayList<>();
            for(MenuComponent comp : symmetriesList.components){
                if(((MenuComponentSymmetry)comp).enabled)newSymmetries.add(((MenuComponentSymmetry)comp).symmetry);
            }
            symmetries = newSymmetries;
            ArrayList<Priority> newPriorities = new ArrayList<>();
            for(MenuComponent comp : prioritiesList.components){
                newPriorities.add(((MenuComponentPriority)comp).priority);
            }
            priorities = newPriorities;//to avoid concurrentModification
            ArrayList<PostProcessingEffect> newEffects = new ArrayList<>();
            for(MenuComponent comp : postProcessingEffectsList.components){
                if(((MenuComponentPostProcessingEffect)comp).enabled)newEffects.add(((MenuComponentPostProcessingEffect)comp).postProcessingEffect);
            }
            postProcessingEffects = newEffects;
            changeChancePercent = Float.parseFloat(StandardGenerator.this.changeChance.text);
            variableRate = StandardGenerator.this.variableRate.enabled;
            lockCore = StandardGenerator.this.lockCore.enabled;
            fillAir = StandardGenerator.this.fillAir.enabled;
        }
        private float getChangeChance(){
            return changeChancePercent/100;
        }
    }
}