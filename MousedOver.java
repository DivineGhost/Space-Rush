public interface MousedOver{

    default boolean mouseOver(int mouseX, int mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight){
        if(mouseX > buttonX && mouseX < buttonX + buttonWidth){
            if(mouseY > buttonY && mouseY < buttonY + buttonHeight){
                return true;
            }
        }
        return false;
    }
}