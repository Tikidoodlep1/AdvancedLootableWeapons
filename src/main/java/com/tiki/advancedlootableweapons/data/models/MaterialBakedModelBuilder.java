package com.tiki.advancedlootableweapons.data.models;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.client.ALWClient;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MaterialBakedModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T>
{
    public static <T extends ModelBuilder<T>> MaterialBakedModelBuilder<T> begin(T parent, ExistingFileHelper existingFileHelper)
    {
        return new MaterialBakedModelBuilder<>(parent, existingFileHelper);
    }

    String folder;

    protected MaterialBakedModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
        super(ALWClient.MATERIAL_LOADER, parent, existingFileHelper);
    }
    public MaterialBakedModelBuilder<T> folder(String folder) {
        Preconditions.checkNotNull(folder, "folder must not be null");
        this.folder = folder;
        return this;
    }

    @Override
    public JsonObject toJson(JsonObject json) {
        json = super.toJson(json);
        json.addProperty("folder",folder);
        return json;
    }
}