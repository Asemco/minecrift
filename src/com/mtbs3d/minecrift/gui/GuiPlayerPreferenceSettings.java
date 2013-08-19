package com.mtbs3d.minecrift.gui;

import com.mtbs3d.minecrift.api.IHMDInfo;
import com.mtbs3d.minecrift.api.PluginManager;
import com.mtbs3d.minecrift.settings.VRSettings;

import de.fruitfly.ovr.UserProfileData;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pete
 * Date: 7/4/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class GuiPlayerPreferenceSettings extends BaseGuiSettings implements GuiEventEx
{
    static EnumOptions[] playerOptionsNoProfile = new EnumOptions[] {
            EnumOptions.DUMMY,
            EnumOptions.DUMMY,
            EnumOptions.IPD,
            EnumOptions.EYE_HEIGHT,
            EnumOptions.EYE_PROTRUSION,
            EnumOptions.NECK_LENGTH,
            EnumOptions.RENDER_OWN_HEADWEAR,
            EnumOptions.RENDER_PLAYER_OFFSET,
            EnumOptions.RENDER_FULL_FIRST_PERSON_MODEL,
    };

    static EnumOptions[] playerOptionsWithProfile = new EnumOptions[] {
            EnumOptions.OCULUS_PROFILE_NAME,
            EnumOptions.OCULUS_PROFILE_GENDER,
            EnumOptions.IPD,
            EnumOptions.EYE_HEIGHT,
            EnumOptions.EYE_PROTRUSION,
            EnumOptions.NECK_LENGTH,
            EnumOptions.RENDER_OWN_HEADWEAR,
            EnumOptions.RENDER_PLAYER_OFFSET,
            EnumOptions.RENDER_FULL_FIRST_PERSON_MODEL,
    };

    public GuiPlayerPreferenceSettings(GuiScreen guiScreen, VRSettings guivrSettings) {
        super( guiScreen, guivrSettings );
        screenTitle = "Player Preferences";
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        UserProfileData profile = null;
        boolean enableProfileButton = false;

        if (Minecraft.getMinecraft().vrRenderer != null &&
            Minecraft.getMinecraft().hmdInfo != null)
        {
            // Gets the current profile
            profile = Minecraft.getMinecraft().hmdInfo.getProfileData();
            if (profile != null)
            {
                this.guivrSettings.setOculusProfileIpd(profile._ipd);
                this.guivrSettings.setOculusProfilePlayerEyeHeight(profile._eyeHeight);
                this.guivrSettings.oculusProfileName = profile._name;
                this.guivrSettings.oculusProfileGender = profile.getGenderString();
                enableProfileButton = true;
            }
        }

        if (profile == null)
        {
            this.guivrSettings.useOculusProfile = false;
        }

        StringTranslate stringTranslate = StringTranslate.getInstance();
        this.buttonList.clear();

        // Profile on/off
        GuiSmallButtonEx profileOnOff = new GuiSmallButtonEx(EnumOptions.OCULUS_PROFILE.returnEnumOrdinal(), this.width / 2 - 78, this.height / 6 - 14, EnumOptions.OCULUS_PROFILE, this.guivrSettings.getKeyBinding(EnumOptions.OCULUS_PROFILE));
        profileOnOff.setEventHandler(this);
        profileOnOff.enabled = enableProfileButton;
        this.buttonList.add(profileOnOff);

        this.buttonList.add(new GuiButtonEx(201, this.width / 2 - 100, this.height / 6 + 148, "Reset To Defaults"));
        this.buttonList.add(new GuiButtonEx(200, this.width / 2 - 100, this.height / 6 + 168, stringTranslate.translateKey("gui.done")));
        EnumOptions[] buttons = null;

        if (this.guivrSettings.useOculusProfile)
            buttons = playerOptionsWithProfile;
        else
            buttons = playerOptionsNoProfile;

        for (int var12 = 2; var12 < buttons.length + 2; ++var12)
        {
            EnumOptions var8 = buttons[var12 - 2];
            if (var8 == EnumOptions.DUMMY)
                continue;

            int width = this.width / 2 - 155 + var12 % 2 * 160;
            int height = this.height / 6 + 21 * (var12 / 2) - 10;

            if (var8.getEnumFloat())
            {
                float minValue = 0.0f;
                float maxValue = 1.0f;
                float increment = 0.01f;

                if (var8 == EnumOptions.EYE_HEIGHT)
                {
                    minValue = 1.62f;
                    maxValue = 1.85f;
                    increment = 0.01f;
                }

                if (var8 == EnumOptions.RENDER_PLAYER_OFFSET)
                {
                    minValue = 0.0f;
                    maxValue = 0.25f;
                    increment = 0.01f;
                }

                if (var8 == EnumOptions.IPD)
                {
                    minValue = 0.055f;
                    maxValue = 0.075f;
                    increment = 0.0001f;
                }

                if (var8 == EnumOptions.EYE_PROTRUSION)
                {
                    minValue = 0.00f;
                    maxValue = 0.25f;
                    increment = 0.001f;
                }

                if (var8 == EnumOptions.NECK_LENGTH)
                {
                    minValue = 0.00f;
                    maxValue = 0.25f;
                    increment = 0.001f;
                }

                GuiSliderEx slider = new GuiSliderEx(var8.returnEnumOrdinal(), width, height, var8, this.guivrSettings.getKeyBinding(var8), minValue, maxValue, increment, this.guivrSettings.getOptionFloatValue(var8));
                slider.enabled = getEnabledState(var8);
                slider.setEventHandler(this);
                this.buttonList.add(slider);
            }
            else
            {
                GuiSmallButtonEx button = new GuiSmallButtonEx(var8.returnEnumOrdinal(), width, height, var8, this.guivrSettings.getKeyBinding(var8));
                button.enabled = getEnabledState(var8);
                button.setEventHandler(this);
                this.buttonList.add(button);
            }
        }
    }

    private boolean getEnabledState(EnumOptions e)
    {
        if (this.guivrSettings.useOculusProfile)
        {
            if (e == EnumOptions.IPD ||
                e == EnumOptions.EYE_HEIGHT ||
                e == EnumOptions.OCULUS_PROFILE_NAME ||
                e == EnumOptions.OCULUS_PROFILE_GENDER)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id < 200 && par1GuiButton instanceof GuiSmallButtonEx)
            {
                EnumOptions num = EnumOptions.getEnumOptions(par1GuiButton.id);
                this.guivrSettings.setOptionValue(((GuiSmallButtonEx)par1GuiButton).returnEnumOptions(), 1);
                par1GuiButton.displayString = this.guivrSettings.getKeyBinding(EnumOptions.getEnumOptions(par1GuiButton.id));
            }
            else if (par1GuiButton.id == 200)
            {
                this.mc.vrSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            else if (par1GuiButton.id == 201)
            {
                // Set defaults
                this.guivrSettings.setMinecraftIpd(0.0635F);
                mc.hmdInfo.setIPD(this.guivrSettings.getIPD());
                this.guivrSettings.setMinecraftPlayerEyeHeight(1.74f);
                this.guivrSettings.renderHeadWear = false;
                this.guivrSettings.renderFullFirstPersonModel = true;
                this.guivrSettings.renderPlayerOffset = 0.25f;
                this.guivrSettings.eyeProtrusion = 0.185f;
                this.guivrSettings.neckBaseToEyeHeight = 0.225f;

                this.guivrSettings.saveOptions();
                this.reinit = true;
            }
        }
    }

    @Override
    protected String[] getTooltipLines(String displayString, int buttonId)
    {
        EnumOptions e = EnumOptions.getEnumOptions(buttonId);
        if( e != null )
            switch(e)
            {
                case IPD:
                    return new String[] {
                            "Interpupillary Distance",
                            "  How far apart your eye pupils are, in millimeters"} ;
                case RENDER_OWN_HEADWEAR:
                    return new String[] {
                            "Whether to render the player's own headwear or not",
                            "  ON:  Headwear is rendered. May obscure your view!",
                            "  OFF: Not rendered."
                    };
                case RENDER_FULL_FIRST_PERSON_MODEL:
                    return new String[] {
                            "Whether to render the full first-person model, or",
                            "just the disembodied arm or held item (Ctrl-H).",
                            "  Full: A full first-person model is rendered.",
                            "        However some animations may not yet be",
                            "        supported (e.g. holding a map).",
                            "  Hand: Only the held item is rendered. You will",
                            "        have no torso!"
                    };
                case RENDER_PLAYER_OFFSET:
                    return new String[] {
                            "Distance your body is rendered back from the normal",
                            "position.",
                            "  The current Steve player model can obscure your",
                            "  peripheral view when rendered at the normal",
                            "  Minecraft position. This setting moves the render",
                            "  position of body backwards by the desired distance,",
                            "  in cm."
                    };
                case EYE_HEIGHT:
                    return new String[] {
                            "Your real-world Eye Height when standing (in meters)",
                            "  Setting this value isn't required, but you should",
                            "  strive to get it as close as possible for an accurate",
                            "  experience"
                    };
                case EYE_PROTRUSION:
                    return new String[] {
                            "Distance from \"head-center\" to your eyes (in meters)",
                            "  (with pos track affects origin position only)",
                            " (\"X\" distance below)     ____  ",
                            "                              /      \\ ",
                            "                              |    XXo ",
                            "                              |      _\\",
                            "                               \\   /",
                            "                                 | |"
                    };
                case NECK_LENGTH:
                    return new String[] {
                            "Distance from \"head-center\" to your shoulders",
                            "  (with pos track affects origin position only)",
                            " (\"Y\" distance below)     ____  ",
                            "                              /      \\ ",
                            "                              |   Y  o ",
                            "                              |   Y  _\\",
                            "                               \\ Y /",
                            "                                 |Y|"
                    };
                default:
                    return null;
            }
        else
            switch(buttonId)
            {
//                case 201:
//                    return new String[] {
//                            "Open this configuration screen to adjust the Head",
//                            "  Tracker orientation (direction) settings. ",
//                            "  Ex: Head Tracking Selection (Hydra/Oculus), Prediction"
//                    };
                default:
                    return null;
            }
    }

    @Override
    public void event(int id, EnumOptions enumm)
    {
        if (enumm == EnumOptions.IPD)
        {
            mc.hmdInfo.setIPD(this.mc.vrSettings.getIPD());
        }
        if (enumm == EnumOptions.OCULUS_PROFILE)
        {
            this.reinit = true;
        }
    }
}
