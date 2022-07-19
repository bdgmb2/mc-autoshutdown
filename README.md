# Minecraft Server Stopper

This is a Minecraft Forge server-side mod that stops the running server when
there are no players online. There is a 5-minute grace time when the server
starts up for players to join, then checks every 30-seconds after. Players who
lose connection have a 30-second grace period to re-join the server before it
shuts down.

### Scripting
This is designed to run on an Amazon EC2 VPS. When shutting down, the mod writes a
`.stopserver` file to the `ec2-user`'s home directory. The existence of this file can
then be checked by scripts or other programs to perform other actions.