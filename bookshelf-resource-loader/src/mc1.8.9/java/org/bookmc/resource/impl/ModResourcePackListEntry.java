package org.bookmc.resource.impl;

import com.google.gson.JsonParseException;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bookmc.loader.api.mod.ModContainer;

import java.io.IOException;

public class ModResourcePackListEntry extends ResourcePackListEntry {
    private static final Logger LOGGER = LogManager.getLogger();
    private final IResourcePack field_148320_d;
    private final ResourceLocation resourcePackIcon;

    private final ModContainer container;

    public ModResourcePackListEntry(ModContainer container, GuiScreenResourcePacks var1) {
        super(var1);
        this.container = container;
        this.field_148320_d = ModResourcePackManager.getResourcePackMap().get(container.getMetadata().getId());

        DynamicTexture var2;
        try {
            var2 = new DynamicTexture(this.field_148320_d.getPackImage());
        } catch (IOException var4) {
            var2 = TextureUtil.missingTexture;
        }

        this.resourcePackIcon = this.mc.getTextureManager().getDynamicTextureLocation("texturepackicon", var2);
    }

    protected int func_183019_a() {
        return 1;
    }

    protected String func_148311_a() {
        try {
            PackMetadataSection var1 = this.field_148320_d.getPackMetadata(this.mc.getResourcePackRepository().rprMetadataSerializer, "pack");
            if (var1 != null) {
                return var1.getPackDescription().getFormattedText();
            }
        } catch (JsonParseException | IOException var2) {
            LOGGER.error("Couldn't load metadata info", var2);
        }
        return EnumChatFormatting.RED + "Missing " + "pack.mcmeta" + " :(";
    }

    protected boolean func_148309_e() {
        return false;
    }

    protected boolean func_148308_f() {
        return false;
    }

    protected boolean func_148314_g() {
        return false;
    }

    protected boolean func_148307_h() {
        return false;
    }

    protected String func_148312_b() {
        return container.getMetadata().getName();
    }

    protected void func_148313_c() {
        this.mc.getTextureManager().bindTexture(this.resourcePackIcon);
    }

    protected boolean func_148310_d() {
        return false;
    }
}
