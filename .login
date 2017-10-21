# Prototype user .login file
#

# This file sources a system-wide .login file, which:
#      - presumes that the .cshrc file has been sourced

if (-e /usr/athena/lib/init) then
	set initdir=/usr/athena/lib/init
else
	set initdir=/usr/lib/init
endif

if (-r $initdir/login) then
        source $initdir/login
else
	echo "Warning: System-wide initialization files not found."
       	echo "Login initialization has not been performed."
endif


# To adjust the environment initialization sequence, see the instructions in
# the .cshrc file.

# If you want to CHANGE the login initialization sequence, revise this .login
# file (the one you're reading now).  You may want to copy the contents of
# the system-wide login file as a starting point.
