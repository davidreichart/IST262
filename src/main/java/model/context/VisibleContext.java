package model.context;

import model.data.UserFile;

import java.util.ArrayList;

public final class VisibleContext {

    private ArrayList<UserFile> activeFiles;

    public VisibleContext() {
        if (this.activeFiles == null) {
            this.activeFiles = new ArrayList<>();
        }
    }

    
}
