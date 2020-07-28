package discord.play.smivilization.thing;
import discord.play.smivilization.HutThing;
import discord.play.smivilization.HutThingColorable;
import java.awt.Color;
import planner.Core;
import simplelibrary.opengl.ImageStash;
import simplelibrary.opengl.Renderer2D;
public class Bed extends HutThingColorable{
    public Bed(){
        super("Bed", "bed", 24, Color.white);
    }
    @Override
    public HutThing newInstance(){
        return new Bed();
    }
    @Override
    public void render(int width, int height){
        Core.applyColor(Color.white);
        Renderer2D.drawRect(0, 0, width, height, ImageStash.instance.getTexture("/textures/smivilization/buildings/huts/gliese/furniture/bed/frame.png"));
        Renderer2D.drawRect(0, 0, width, height, ImageStash.instance.getTexture("/textures/smivilization/buildings/huts/gliese/furniture/bed/matress.png"));
        Renderer2D.drawRect(0, 0, width, height, ImageStash.instance.getTexture("/textures/smivilization/buildings/huts/gliese/furniture/bed/pillow.png"));
        Core.applyColor(getColor());
        Renderer2D.drawRect(0, 0, width, height, ImageStash.instance.getTexture("/textures/smivilization/buildings/huts/gliese/furniture/bed/sheets.png"));
    }
    
}