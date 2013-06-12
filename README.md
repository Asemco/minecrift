Minecrift Mod for Minecraft
===========================

Current Version: 0.27 alpha

StellaArtois, mabrowning 2013

With thanks to:

- Palmer Luckey and his team for creating the Oculus Rift. The future is
  finally here (well for some people anyway; mine hasn't arrived yet).
- Markus "Notch" Persson for creating Minecraft. What has it grown into?
- The team behind the MCP coders' pack, and the Minecraft community - why
  Mojang bother obsfucating the source when you guys have done such a fantastic
  job of de-obsfucating it is beyond me!
- Powback for his initial work on the Java JNI wrapper to the SDK. Seeing this
  inspired me to get off my arse and get modding. See
  [this Reddit thread](http://www.reddit.com/r/oculus/comments/1c1vh0/java_wrapper_for_devs/)
- shakesoda and Ben (and others?) at MTBS for creating the GLSL version of the
  oculus distortion shader.
- The guys at Valve for giving some good advice on updating a game for VR.
- @PyramidHead76 for building the MacOS libs, and toiling to produce the
  installation guide!!
- Brad Larson and his GPUImage library, for the Lanczos GLSL shader
  implementation for the FSAA.
- All the feedback and support of the folks in the MTBS3D forums!

What is Minecrift?
------------------

The cheesy name apart, Minecrift attempts to update Minecraft to support the
Oculus Rift. Initially this means allowing headtracking input and using the
correct stereo rendering parameters for the Rift. In the future this also means
updating Minecraft for various control schemes. Minecrift is also meant as a
kick up the arse to Mojang, so that they can add official Oculus support in the
near future. If and when Minecraft officially supports the Rift, Minecrift
development might cease (unless they make a complete hash of it).


Disclaimer
----------

I recommend using a vanila Minecraft.jar file for this. Forge compatibility is
mostly in place, but there may be a bugs.  BACK UP your original minecraft.jar
before installing this mod. I've gotten FTB 1.5.2 to start up and run, but
haven't tested all the nooks and crannies of the mod. Caveat Modder.

---

Installation
------------

REQUIRES Minecraft 1.5.2 With [Optifine HD D3](http://www.minecraftforum.net/topic/249637-152-optifine-hd-d3-fps-boost-hd-textures-aa-af-and-much-more/)

Magic Launcher
--------------
The recommended way to install Minecrift is use the [magic
launcher](http://www.minecraftforum.net/topic/939149-launcher-magic-launcher-114-mods-options-profiles-news/),
which is available for Windows, OSX, and Linux.

- Download Optifine HD D3, but don't extract.
- Extract the minecrift\_0\_26.zip
- Open the Magic Launcher.
- Click the 'Setup' configuration button.
- Create a new Configuration and call it "minecrift" (or whatever you prefer)
- Add these zips, in order:
  - OptiFine\_1.5.2\_HD\_U\_D3.zip 
  - JRift.jar 
  - minecrift\_0\_27\_classes.zip 
- Click 'Test' to make sure it works.
- When satisfied, click 'OK' to Save the configuration.
- From now on, just start Magic Launcher and use the "minecrift" configuration to play!


In addition, I *Strongly* recommend you get updated
[LWJGL](http://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.0/)
and [OpenAL](http://kcat.strangesoft.net/openal.html) libraries for the best
possible experience.  Minecrift was developed with LWJGL 2.9.0 and OpenAL
1.15.1. Using older versions may yield unpleasant results.


Manual
------

It is possible to install Minecrift without using the Magic launcher, but this
way hasn't been tested as well. Use the steps below according to your operating
system.

Windows
-------

Minecrift for Windows requires Vista or above and a graphics card & driver capable of at least OpenGL 3.3 support.

- Download [Optifine HD D3](http://www.minecraftforum.net/topic/249637-152-optifine-hd-d3-fps-boost-hd-textures-aa-af-and-much-more/)
- Change directory to %APPDATA%\\.minecraft\bin
- Open your minecraft.jar file using 7-zip, winzip etc. 
- Select all, and drag and drop in the *entire contents* of the
  OptiFine\_1.5.2\_HD\_U\_D3.zip into the minecraft.jar.
- Select all, and drag and drop in the *entire contents* of the
  /minecrift\_0\_27\_classes.zip (but not the zip itself) from the Minecrift
  zip into the minecraft.jar archive.
- Select all, and drag and drop in the *entire contents* of the
  /JRift.jar (but not the zip itself) from the Minecrift
  zip into the minecraft.jar archive.
- Make sure to delete the META-INF folder in minecraft.jar. Close 7zip /
  winzip.
- *IMPORTANT* (but only required once). Install the Microsoft VS2012 C++
  redists (both x86 and x64) from
  [here](http://www.microsoft.com/visualstudio/11/en-us/downloads/vc-redist#vc-redist)
- Start up Minecraft and off you go. If you get a black screen on login, trying
  running an admin command prompt, cd to your minecraft.exe dir and enter the
  command 
>java -cp Minecraft.exe net.minecraft.LauncherFrame
This should allow any exceptions or errors on Minecraft startup to show up in the console.

MacOS
-----

Follow the same steps for Windows, but use ~/Library/Application
Support/minecraft instead of <Path to %APPDATA%>\\.minecraft.
- The VS2012 C++ redistributable is not required.

Linux
-----

Follow the same steps for Windows, but use ~/.minecraft/ instead of %APPDATA%\\.minecraft.

Controls/Usage
--------

Obviously you will want to be at 1280X800 fullscreen for the Oculus Rift DK1. 

- All Minecrift settings are present in the Options->Minecrift screen, but
  keyboard shortcuts are also available for convenience
- F1 to bring up the game HUD / overlay if it isn't already up. 
- Ctrl and - / = for IPD adjustment. Hold ALT as well for fine adjustment. The
  IPD setting should be saved between sessions.
- Ctrl O to attempt to reinitialise the Rift (including head tracking).
- Ctrl P while not in a menu to turn distortion on / off. Sometimes useful if
  the offset mouse pointer is a pain in the menus. Ctrl-Alt P to toggle
  chromatic aberration correction.
- Ctrl L toggles headtracking ON/OFF. Ctrl-Alt L toggles tracking prediction
  ON/OFF. It is OFF by default.
- Ctrl U changes the HUD distance. Ctrl-Alt U changes the HUD scale. Ctrl-Alt Y
  toggles opacity on the HUD.
- Ctrl-M toggles rendering of the player's mask ON/OFF.
- FOV adjustment within Minecraft will have no effect - I use the FOV as
  calculated from within the Oculus SDK.
- Allow user to use mouse pitch input as well as yaw. Use Ctrl-N to toggle.
- Large or Auto GUI size recommended.
- Use Ctrl-B to turn Full Scene Anti-aliasing (FSAA) on/off. Use Ctrl-Alt B to
  cycle the FSAA renderscale. Be warned; this feature is a resource hog!! If
  you cannot get 60fps at your desired FSSA level, cycle it to a lower scale
  factor. Anyone with a nVidia GTX Titan please let me know what average FPS
  you get at scale factor 4.0!
- Ctrl , or . decreases or increases the FOV scale factor. This can be used to
  fine tune FOV if it doesn't look quite right to you.
- Ctrl-Alt , or . decreases or increases various sizes of distortion border.
  This can be used to improve rendering speed, at a potential loss of FOV.
- Ctrl V cycles through head track sensitivity multipliers. Try this at your
  own risk!

Known Issues
------------

There are (not so) many.

- FSAA (Super Sampling) doens't work on OSX and is disabled.
- Linux doesn't support Oculus Rift head tracker (yet).
- Rarely, water and other effects are misrendered for a few frames.
- A white line can sometimes be seen at the top or bottom edge of the HUD. No
  known workaround.


Feedback, bug reporting
-----------------------

Please post feedback, bug reports etc. to the [forum thread at
MTBS](http://www.mtbs3d.com/phpbb/viewtopic.php?f=140&t=17146)

Roadmap
-------

- Alternate control scheme options; head look separate to body movement etc.
- Investigate gamepad / Razor Hydra support.
- Fix bugs.

Release Notes
=============

The changelist can be seen [here](CHANGES.md)

---

Building
========


The installation process has been tested on Linux, but was written with
cross-platform support in mind, so should be usable on other platforms.
Known issues: OSX fernflower doesn't decompile cleanly. Decompile on Windows
or Linux and copy over the .minecraft\_orig folder.

Download [mcp 751](http://mcp.ocean-labs.de/index.php/MCP_Releases) and
extract into /mcp (only needed once)

Run install.sh (or install.bat) to download minecraft, download optifine,
deobfuscate the base system, and apply the patches and new files.

Use the MCP environment in /mcp to modify, test, and recompile.

Run build.sh (or build.bat) to create a release classes.zip.

Run getchanges.sh (or getchanges.bat) to diff the modified /mcp/src files into
version controlled /patches and copy the new classes into the /src/
directory.

License
-------

See [The License File](LICENSE.md) for more information.
