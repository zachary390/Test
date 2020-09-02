package planner.menu.configuration.overhaul.fissionsfr;
import multiblock.configuration.Configuration;
import planner.Core;
import planner.menu.component.MenuComponentMinimalistButton;
import planner.menu.component.MenuComponentMinimalistTextBox;
import static simplelibrary.opengl.Renderer2D.drawText;
import simplelibrary.opengl.gui.GUI;
import simplelibrary.opengl.gui.Menu;
public class MenuFissionSFRConfiguration extends Menu{
    private final MenuComponentMinimalistButton blocks = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Blocks", true, true));
    private final MenuComponentMinimalistButton fuels = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Fuels", true, true));
    private final MenuComponentMinimalistButton sources = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Sources", true, true));
    private final MenuComponentMinimalistButton irradiatorRecipes = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Irradiator Recipes", true, true));
    private final MenuComponentMinimalistButton coolantRecipes = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Coolant Recipes", true, true));
    private final MenuComponentMinimalistTextBox minSize = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true).setIntFilter());
    private final MenuComponentMinimalistTextBox maxSize = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true).setIntFilter());
    private final MenuComponentMinimalistTextBox neutronReach = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true).setIntFilter());
    private final MenuComponentMinimalistTextBox coolingEfficiencyLeniency = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true).setIntFilter());
    private final MenuComponentMinimalistTextBox sparsityPenaltyMult = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true).setFloatFilter());
    private final MenuComponentMinimalistTextBox sparsityPenaltyThreshold = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true).setFloatFilter());
    private final MenuComponentMinimalistButton back = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Back", true, true));
    private final Configuration configuration;
    public MenuFissionSFRConfiguration(GUI gui, Menu parent, Configuration configuration){
        super(gui, parent);
        blocks.addActionListener((e) -> {
            gui.open(new MenuBlocksConfiguration(gui, this, configuration));
        });
        fuels.addActionListener((e) -> {
            gui.open(new MenuFuelsConfiguration(gui, this, configuration));
        });
        sources.addActionListener((e) -> {
            gui.open(new MenuSourcesConfiguration(gui, this, configuration));
        });
        irradiatorRecipes.addActionListener((e) -> {
            gui.open(new MenuIrradiatorRecipesConfiguration(gui, this, configuration));
        });
        coolantRecipes.addActionListener((e) -> {
            gui.open(new MenuCoolantRecipesConfiguration(gui, this, configuration));
        });
        back.addActionListener((e) -> {
            gui.open(parent);
        });
        this.configuration = configuration;
    }
    @Override
    public void onGUIOpened(){
        blocks.label = "Blocks ("+configuration.overhaul.fissionSFR.blocks.size()+")";
        fuels.label = "Fuels ("+configuration.overhaul.fissionSFR.fuels.size()+")";
        sources.label = "Sources ("+configuration.overhaul.fissionSFR.sources.size()+")";
        irradiatorRecipes.label = "Irradiator Recipes ("+configuration.overhaul.fissionSFR.irradiatorRecipes.size()+")";
        coolantRecipes.label = "Coolant Recipes ("+configuration.overhaul.fissionSFR.coolantRecipes.size()+")";
        minSize.text = configuration.overhaul.fissionSFR.minSize+"";
        maxSize.text = configuration.overhaul.fissionSFR.maxSize+"";
        neutronReach.text = configuration.overhaul.fissionSFR.neutronReach+"";
        coolingEfficiencyLeniency.text = configuration.overhaul.fissionSFR.coolingEfficiencyLeniency+"";
        sparsityPenaltyMult.text = configuration.overhaul.fissionSFR.sparsityPenaltyMult+"";
        sparsityPenaltyThreshold.text = configuration.overhaul.fissionSFR.sparsityPenaltyThreshold+"";
    }
    @Override
    public void onGUIClosed(){
        configuration.overhaul.fissionSFR.minSize = Integer.parseInt(minSize.text); 
        configuration.overhaul.fissionSFR.maxSize = Integer.parseInt(maxSize.text);
        configuration.overhaul.fissionSFR.neutronReach = Integer.parseInt(neutronReach.text);
        configuration.overhaul.fissionSFR.coolingEfficiencyLeniency = Integer.parseInt(coolingEfficiencyLeniency.text);
        configuration.overhaul.fissionSFR.sparsityPenaltyMult = Float.parseFloat(sparsityPenaltyMult.text);
        configuration.overhaul.fissionSFR.sparsityPenaltyThreshold = Float.parseFloat(sparsityPenaltyThreshold.text);
    }
    @Override
    public void render(int millisSinceLastTick){
        minSize.width = maxSize.width = neutronReach.width = coolingEfficiencyLeniency.width = sparsityPenaltyMult.width = sparsityPenaltyThreshold.width = Core.helper.displayWidth()*.75;
        minSize.x = maxSize.x = neutronReach.x = coolingEfficiencyLeniency.x = sparsityPenaltyMult.x = sparsityPenaltyThreshold.x = Core.helper.displayWidth()*.25;
        blocks.width = fuels.width = sources.width = irradiatorRecipes.width = coolantRecipes.width = back.width = Core.helper.displayWidth();
        minSize.height = maxSize.height = neutronReach.height = coolingEfficiencyLeniency.height = sparsityPenaltyMult.height = sparsityPenaltyThreshold.height = blocks.height = fuels.height = sources.height = irradiatorRecipes.height = coolantRecipes.height = back.height = Core.helper.displayHeight()/16;
        fuels.y = blocks.height;
        sources.y = fuels.y+fuels.height;
        irradiatorRecipes.y = sources.y+sources.height;
        coolantRecipes.y = irradiatorRecipes.y+irradiatorRecipes.height;
        minSize.y = coolantRecipes.y+coolantRecipes.height;
        maxSize.y = minSize.y+minSize.height;
        neutronReach.y = maxSize.y+maxSize.height;
        coolingEfficiencyLeniency.y = neutronReach.y+neutronReach.height;
        sparsityPenaltyMult.y = coolingEfficiencyLeniency.y+coolingEfficiencyLeniency.height;
        sparsityPenaltyThreshold.y = sparsityPenaltyMult.y+sparsityPenaltyMult.height;
        back.y = Core.helper.displayHeight()-back.height;
        if(configuration.addon){
            minSize.y = maxSize.y = neutronReach.y = coolingEfficiencyLeniency.y = sparsityPenaltyMult.y = sparsityPenaltyThreshold.y = -minSize.height;
        }
        Core.applyColor(Core.theme.getTextColor());
        drawText(0, minSize.y+Core.helper.displayHeight()/64, Core.helper.displayWidth()*.25, minSize.y+minSize.height-Core.helper.displayHeight()/64, "Minimum reactor size");
        drawText(0, maxSize.y+Core.helper.displayHeight()/64, Core.helper.displayWidth()*.25, maxSize.y+maxSize.height-Core.helper.displayHeight()/64, "Maximum reactor size");
        drawText(0, neutronReach.y+Core.helper.displayHeight()/64, Core.helper.displayWidth()*.25, neutronReach.y+neutronReach.height-Core.helper.displayHeight()/64, "Neutron reach");
        drawText(0, coolingEfficiencyLeniency.y+Core.helper.displayHeight()/64, Core.helper.displayWidth()*.25, coolingEfficiencyLeniency.y+coolingEfficiencyLeniency.height-Core.helper.displayHeight()/64, "Cooling Efficiency Leniency");
        drawText(0, sparsityPenaltyMult.y+Core.helper.displayHeight()/64, Core.helper.displayWidth()*.25, sparsityPenaltyMult.y+sparsityPenaltyMult.height-Core.helper.displayHeight()/64, "Sparsity Penalty Multiplier");
        drawText(0, sparsityPenaltyThreshold.y+Core.helper.displayHeight()/64, Core.helper.displayWidth()*.25, sparsityPenaltyThreshold.y+sparsityPenaltyThreshold.height-Core.helper.displayHeight()/64, "Sparsity Penalty Threshold");
        Core.applyWhite();
        super.render(millisSinceLastTick);
    }
}