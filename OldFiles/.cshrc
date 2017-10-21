# Prototype user .cshrc file
#

# This file sources a system-wide cshrc file, which:
#      - sets up standard environment variables
#      - sources user file ~/.environment, if it exists
#      - sets standard path, OR sources user file ~/.path, if it exists
#      - sets up standard cshell variables, aliases, etc.
#      - source user file ~/.cshrc.mine, if it exists

if (-d /usr/athena/lib/init) then
	set initdir=/usr/athena/lib/init
else
	set initdir=/usr/lib/init
endif

if (-r $initdir/cshrc) then
        source $initdir/cshrc
else
	if ($?prompt) then		# Don't echo if noninteractive
	  echo "Warning: System-wide initialization files not found."
	  echo "C Shell initialization has not been performed."
	  stty dec
	endif
	# set some basic defaults if failed initialization
	umask 077
	set path=( . ~/${hosttype}bin /srvd/patch /usr/athena \
		/bin/athena /usr/bin/X /usr/new /usr/new/mh/bin \
		/usr/ucb /bin /usr/bin /usr/ibm )
endif


# If you want to ADJUST the cshell initialization sequence, create any of 
# the following files (as appropriate) in your home directory, with commands
# to achieve the effect listed:
#
#      .environment - setup session environment (set environmental variables, 
#                     attach lockers, etc.)
#      .path        - set default search path (you can refer to shell
#                     variable $athena_path, which lists athena default path,
#                     when you set path, e.g., "set path=(. $athena_path)")
#      .cshrc.mine  - setup cshell environment (set shell variables, aliases,
#                     unset system defaults, etc.)
