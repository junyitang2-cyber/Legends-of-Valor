//Draw colorful map and UI
import java.util.HashMap;

public class Colors {
    private HashMap<String, String> colors;

    public Colors(){
        colors = new HashMap<>();
        colors.put("red", "\033[0;31m");
        colors.put("green", "\033[0;32m");
        colors.put("yellow", "\033[0;33m");
        colors.put("blue", "\033[0;34m");
        colors.put("purple", "\033[0;35m");
        colors.put("cyan", "\033[0;36m");
        colors.put("white", "\033[0;37m");
        colors.put("original", "\033[0m");
        colors.put("magenta","\033[0;35m");
    }

    public String addColor(String color, String message){
        return colors.get(color) + message;
    }
    public String addColorHOrM(String color, String message, String color2){
        return colors.get(color) + message + colors.get(color2);
    }
}
