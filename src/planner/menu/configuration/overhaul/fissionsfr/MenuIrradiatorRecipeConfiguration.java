package planner.menu.configuration.overhaul.fissionsfr;
import java.awt.Color;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import planner.configuration.overhaul.fissionsfr.IrradiatorRecipe;
import planner.menu.component.MenuComponentMinimalistButton;
import planner.menu.component.MenuComponentMinimalistTextBox;
import simplelibrary.opengl.gui.GUI;
import simplelibrary.opengl.gui.Menu;
public class MenuIrradiatorRecipeConfiguration extends Menu{
    private static final Color textColor = new Color(.1f, .1f, .2f, 1f);
    private final MenuComponentMinimalistTextBox name = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "Name", true));
    private final MenuComponentMinimalistTextBox efficiency = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true));
    private final MenuComponentMinimalistTextBox heat = add(new MenuComponentMinimalistTextBox(0, 0, 0, 0, "", true));
    private final MenuComponentMinimalistButton back = add(new MenuComponentMinimalistButton(0, 0, 0, 0, "Back", true, true));
    private final IrradiatorRecipe irradiatorRecipe;
    public MenuIrradiatorRecipeConfiguration(GUI gui, Menu parent, IrradiatorRecipe irradiatorRecipe){
        super(gui, parent);
        back.addActionListener((e) -> {
            irradiatorRecipe.name = name.text;
            irradiatorRecipe.efficiency = Float.parseFloat(efficiency.text);
            irradiatorRecipe.heat = Float.parseFloat(heat.text);
            gui.open(parent);
        });
        this.irradiatorRecipe = irradiatorRecipe;
    }
    @Override
    public void onGUIOpened(){
        name.text = irradiatorRecipe.name;
        efficiency.text = irradiatorRecipe.efficiency+"";
        heat.text = irradiatorRecipe.heat+"";
    }
    @Override
    public void render(int millisSinceLastTick){
        back.width = name.width = Display.getWidth();
        efficiency.width = heat.width = Display.getWidth()*.75;
        efficiency.x = heat.x = Display.getWidth()*.25;
        efficiency.height = heat.height = name.height = back.height = Display.getHeight()/16;
        efficiency.y = name.height;
        heat.y = efficiency.y+efficiency.height;
        back.y = Display.getHeight()-back.height;
        GL11.glColor4f(textColor.getRed()/255f, textColor.getGreen()/255f, textColor.getBlue()/255f, textColor.getAlpha()/255f);
        drawText(0, Display.getHeight()/16, Display.getWidth()*.25, Display.getHeight()/16*2, "Efficiency");
        drawText(0, Display.getHeight()/16*2, Display.getWidth()*.25, Display.getHeight()/16*3, "Heat");
        GL11.glColor4f(1, 1, 1, 1);
        super.render(millisSinceLastTick);
    }
}