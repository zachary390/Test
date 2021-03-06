package overhaul;
import java.util.ArrayList;
import java.util.HashMap;
public abstract class Configuration{
    public static final Configuration DEFAULT = new Configuration(){
        @Override
        public void init(ArrayList<Fuel> fuels, HashMap<Heatsink, Integer> cooling, HashMap<FuelCell, Float> sourceEff, HashMap<Moderator, Integer> modFlux, HashMap<Moderator, Float> modEff, HashMap<Reflector, Float> refRef, HashMap<Reflector, Float> refEff, HashMap<NeutronShield, Integer> shieldHeat){
            fuels.add(new Fuel("TBU"        ,1.25   ,40     ,234    ,0      ,1.25   ,32     ,293    ,0      ,1.25   ,50     ,199    ,0      ,2.5    ,32     ,234    ,0));
            fuels.add(new Fuel("LEU-233"    ,1.1    ,216    ,78     ,0      ,1.1    ,172    ,98     ,0      ,1.1    ,270    ,66     ,0      ,2.2    ,172    ,78     ,0));
            fuels.add(new Fuel("HEU-233"    ,1.15   ,648    ,39     ,0      ,1.15   ,516    ,49     ,0      ,1.15   ,810    ,33     ,0      ,2.3    ,516    ,39     ,0));
            fuels.add(new Fuel("LEU-235"    ,1      ,120    ,102    ,0      ,1      ,96     ,128    ,0      ,1      ,150    ,87     ,0      ,2      ,96     ,102    ,0));
            fuels.add(new Fuel("HEU-235"    ,1.05   ,360    ,51     ,0      ,1.05   ,288    ,64     ,0      ,1.05   ,450    ,43     ,0      ,2.1    ,288    ,51     ,0));
            fuels.add(new Fuel("LEN-236"    ,1.1    ,292    ,70     ,0      ,1.1    ,234    ,88     ,0      ,1.1    ,366    ,60     ,0      ,2.2    ,234    ,70     ,0));
            fuels.add(new Fuel("HEN-236"    ,1.15   ,876    ,35     ,0      ,1.15   ,702    ,44     ,0      ,1.15   ,1098   ,30     ,0      ,2.3    ,702    ,35     ,0));
            fuels.add(new Fuel("LEP-239"    ,1.2    ,126    ,99     ,0      ,1.2    ,100    ,124    ,0      ,1.2    ,158    ,84     ,0      ,2.4    ,100    ,99     ,0));
            fuels.add(new Fuel("HEP-239"    ,1.25   ,378    ,49     ,0      ,1.25   ,300    ,62     ,0      ,1.25   ,474    ,42     ,0      ,2.5    ,300    ,49     ,0));
            fuels.add(new Fuel("LEP-241"    ,1.25   ,182    ,84     ,0      ,1.25   ,146    ,105    ,0      ,1.25   ,228    ,71     ,0      ,2.5    ,146    ,84     ,0));
            fuels.add(new Fuel("HEP-241"    ,1.3    ,546    ,42     ,0      ,1.3    ,438    ,52     ,0      ,1.3    ,684    ,35     ,0      ,2.6    ,438    ,42     ,0));
            fuels.add(new Fuel("MOX-239"    ,1.05   ,132    ,94     ,0      ,1.05   ,106    ,118    ,0      ,1.05   ,166    ,80     ,0      ,2.1    ,106    ,94     ,0));
            fuels.add(new Fuel("MOX-241"    ,1.15   ,192    ,80     ,0      ,1.15   ,154    ,100    ,0      ,1.15   ,240    ,68     ,0      ,2.3    ,154    ,80     ,0));
            fuels.add(new Fuel("LEA-242"    ,1.35   ,390    ,65     ,0      ,1.35   ,312    ,81     ,0      ,1.35   ,488    ,55     ,0      ,2.7    ,312    ,65     ,0));
            fuels.add(new Fuel("HEA-242"    ,1.4    ,1170   ,32     ,0      ,1.4    ,936    ,40     ,0      ,1.4    ,1464   ,27     ,0      ,2.8    ,936    ,32     ,0));
            fuels.add(new Fuel("LECm-243"   ,1.45   ,384    ,66     ,0      ,1.45   ,308    ,83     ,0      ,1.45   ,480    ,56     ,0      ,2.9    ,308    ,66     ,0));
            fuels.add(new Fuel("HECm-243"   ,1.5    ,1152   ,33     ,0      ,1.5    ,924    ,41     ,0      ,1.5    ,1440   ,28     ,0      ,3      ,924    ,33     ,0));
            fuels.add(new Fuel("LECm-245"   ,1.5    ,238    ,75     ,0      ,1.5    ,190    ,94     ,0      ,1.5    ,298    ,64     ,0      ,3      ,190    ,75     ,0));
            fuels.add(new Fuel("HECm-245"   ,1.55   ,714    ,37     ,0      ,1.55   ,570    ,47     ,0      ,1.55   ,894    ,32     ,0      ,3.1    ,570    ,37     ,0));
            fuels.add(new Fuel("LECm-247"   ,1.55   ,268    ,72     ,0      ,1.55   ,214    ,90     ,0      ,1.55   ,336    ,61     ,0      ,3.1    ,214    ,72     ,0));
            fuels.add(new Fuel("HECm-247"   ,1.6    ,804    ,36     ,0      ,1.6    ,642    ,45     ,0      ,1.6    ,1008   ,30     ,0      ,3.2    ,642    ,36     ,0));
            fuels.add(new Fuel("LEB-248"    ,1.65   ,266    ,73     ,0      ,1.65   ,212    ,91     ,0      ,1.65   ,332    ,62     ,0      ,3.3    ,212    ,73     ,0));
            fuels.add(new Fuel("HEB-248"    ,1.7    ,798    ,36     ,0      ,1.7    ,636    ,45     ,0      ,1.7    ,996    ,31     ,0      ,3.4    ,636    ,36     ,0));
            fuels.add(new Fuel("LECf-249"   ,1.75   ,540    ,60     ,1      ,1.75   ,432    ,75     ,1      ,1.75   ,676    ,51     ,1      ,3.5    ,432    ,60     ,1));
            fuels.add(new Fuel("HECf-249"   ,1.8    ,1620   ,30     ,1      ,1.8    ,1296   ,37     ,1      ,1.8    ,2028   ,25     ,1      ,3.6    ,1296   ,30     ,1));
            fuels.add(new Fuel("LECf-251"   ,1.8    ,288    ,71     ,1      ,1.8    ,230    ,89     ,1      ,1.8    ,360    ,60     ,1      ,3.6    ,230    ,71     ,1));
            fuels.add(new Fuel("HECf-251"   ,1.85   ,864    ,35     ,1      ,1.85   ,690    ,44     ,1      ,1.85   ,1080   ,30     ,1      ,3.7    ,690    ,35     ,1));
        }
    };
    public static final Configuration CUSTOM = new Configuration(){
        @Override
        public void init(ArrayList<Fuel> fuels, HashMap<Heatsink, Integer> cooling, HashMap<FuelCell, Float> sourceEff, HashMap<Moderator, Integer> modFlux, HashMap<Moderator, Float> modEff, HashMap<Reflector, Float> refRef, HashMap<Reflector, Float> refEff, HashMap<NeutronShield, Integer> shieldHeat){
            DEFAULT.init(fuels, cooling, sourceEff, modFlux, modEff, refRef, refEff, shieldHeat);
        }
    };
    public ArrayList<Fuel> fuels = new ArrayList<>();
    public HashMap<Heatsink, Integer> cooling = new HashMap<>();
    public HashMap<FuelCell, Float> sourceEff = new HashMap<>();
    public HashMap<Moderator, Integer> modFlux = new HashMap<>();
    public HashMap<Moderator, Float> modEff = new HashMap<>();
    public HashMap<Reflector, Float> refRef = new HashMap<>();
    public HashMap<Reflector, Float> refEff = new HashMap<>();
    public HashMap<NeutronShield, Integer> shieldHeat = new HashMap<>();
    public static boolean load(Configuration config){
        if(Main.running)return false;
        Fuel.fuels.clear();
        Fuel.fuels.addAll(config.fuels);
        for(Heatsink h : config.cooling.keySet()){
            h.cooling = config.cooling.get(h);
        }
        FuelCell.BEST_CELL = null;
        for(FuelCell c : config.sourceEff.keySet()){
            c.efficiency = config.sourceEff.get(c);
            if(FuelCell.BEST_CELL==null||c.efficiency>FuelCell.BEST_CELL.efficiency)FuelCell.BEST_CELL = c;
        }
        for(Moderator m : config.modFlux.keySet()){
            m.fluxFactor = config.modFlux.get(m);
        }
        for(Moderator m : config.modEff.keySet()){
            m.efficiencyFactor = config.modEff.get(m);
        }
        for(Reflector r : config.refRef.keySet()){
            r.reflectivity = config.refRef.get(r);
        }
        for(Reflector r : config.refEff.keySet()){
            r.efficiency = config.refEff.get(r);
        }
        for(NeutronShield s : config.shieldHeat.keySet()){
            s.heatMult = config.shieldHeat.get(s);
        }
        if(Main.instance!=null){
            Main.instance.refreshFuels();
        }
        return true;
    }
    public Configuration(){
        cooling.put(ReactorPart.HEATSINK_WATER, 55);
        cooling.put(ReactorPart.HEATSINK_IRON, 50);
        cooling.put(ReactorPart.HEATSINK_REDSTONE, 85);
        cooling.put(ReactorPart.HEATSINK_QUARTZ, 80);
        cooling.put(ReactorPart.HEATSINK_OBSIDIAN, 70);
        cooling.put(ReactorPart.HEATSINK_NETHER_BRICK, 105);
        cooling.put(ReactorPart.HEATSINK_GLOWSTONE, 90);
        cooling.put(ReactorPart.HEATSINK_LAPIS, 100);
        cooling.put(ReactorPart.HEATSINK_GOLD, 110);
        cooling.put(ReactorPart.HEATSINK_PRISMARINE, 115);
        cooling.put(ReactorPart.HEATSINK_SLIME, 145);
        cooling.put(ReactorPart.HEATSINK_END_STONE, 65);
        cooling.put(ReactorPart.HEATSINK_PURPUR, 95);
        cooling.put(ReactorPart.HEATSINK_DIAMOND, 200);
        cooling.put(ReactorPart.HEATSINK_EMERALD, 195);
        cooling.put(ReactorPart.HEATSINK_COPPER, 75);
        cooling.put(ReactorPart.HEATSINK_TIN, 120);
        cooling.put(ReactorPart.HEATSINK_LEAD, 60);
        cooling.put(ReactorPart.HEATSINK_BORON, 160);
        cooling.put(ReactorPart.HEATSINK_LITHIUM, 130);
        cooling.put(ReactorPart.HEATSINK_MAGNESIUM, 125);
        cooling.put(ReactorPart.HEATSINK_MANGANESE, 150);
        cooling.put(ReactorPart.HEATSINK_ALUMINUM, 175);
        cooling.put(ReactorPart.HEATSINK_SILVER, 170);
        cooling.put(ReactorPart.HEATSINK_FLUORITE, 165);
        cooling.put(ReactorPart.HEATSINK_VILLIAUMITE, 180);
        cooling.put(ReactorPart.HEATSINK_CAROBBIITE, 140);
        cooling.put(ReactorPart.HEATSINK_ARSENIC, 135);
        cooling.put(ReactorPart.HEATSINK_NITROGEN, 185);
        cooling.put(ReactorPart.HEATSINK_HELIUM, 190);
        cooling.put(ReactorPart.HEATSINK_ENDERIUM, 155);
        cooling.put(ReactorPart.HEATSINK_CRYOTHEUM, 205);
        sourceEff.put(ReactorPart.FUEL_CELL_RA_BE, .9f);
        sourceEff.put(ReactorPart.FUEL_CELL_PO_BE, .95f);
        sourceEff.put(ReactorPart.FUEL_CELL_CF_252, 1f);
        modFlux.put(ReactorPart.GRAPHITE, 10);
        modFlux.put(ReactorPart.BERYLLIUM, 22);
        modFlux.put(ReactorPart.HEAVY_WATER, 36);
        modFlux.put(ReactorPart.SHIELD_BORON_SILVER, 0);
        modEff.put(ReactorPart.GRAPHITE, 1.1f);
        modEff.put(ReactorPart.BERYLLIUM, 1.05f);
        modEff.put(ReactorPart.HEAVY_WATER, 1f);
        modEff.put(ReactorPart.SHIELD_BORON_SILVER, 0.5f);
        shieldHeat.put(ReactorPart.SHIELD_BORON_SILVER, 5);
        refRef.put(ReactorPart.REFLECTOR_LEAD_STEEL, .5f);
        refRef.put(ReactorPart.REFLECTOR_BERYLLIUM_CARBON, 1f);
        refEff.put(ReactorPart.REFLECTOR_LEAD_STEEL, .25f);
        refEff.put(ReactorPart.REFLECTOR_BERYLLIUM_CARBON, .5f);
        init(fuels, cooling, sourceEff, modFlux, modEff, refRef, refEff, shieldHeat);
    }
    public abstract void init(ArrayList<Fuel> fuels, HashMap<Heatsink, Integer> cooling, HashMap<FuelCell, Float> sourceEff, HashMap<Moderator, Integer> modFlux, HashMap<Moderator, Float> modEff, HashMap<Reflector, Float> refRef, HashMap<Reflector, Float> refEff, HashMap<NeutronShield, Integer> shieldHeat);
}