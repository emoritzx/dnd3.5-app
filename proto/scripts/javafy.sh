#!/bin/bash -up

function ucfirst
{
    printf ${1^}
}

function forward
{
    for token in $(echo $@ | sed "s/_/ /g");
    do
        ucfirst $token
    done
    echo
}

function reverse
{
    echo $@ | sed -e 's/\([A-Z]\)/_\L\1/g' -e 's/^_//'
}

REVERSE=0

while getopts ":r" opt;
do
    case $opt in
        r)
            REVERSE=1
            shift
        ;;
        *)
            # skip
        ;;
    esac
done

for raw_string in $@;
do
    if (( $REVERSE ));
    then
        reverse $raw_string
    else
        forward $raw_string
    fi
done
