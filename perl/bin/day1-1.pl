#!/usr/bin/env perl
use Mojo::Base -strict;

my $file = defined $ARGV[0] ? $ARGV[0] : 'inputs/day1';
$file = "inputs/day1-$file" if $file =~ /test/;
open(my $fh, '<', $file) or die $!;
while (<$fh>) {
    chomp;
    my @chars = split //;
    my $up = scalar grep { $_ eq '(' } @chars;
    my $down = scalar grep { $_ eq ')' } @chars;
    say $up - $down;
}
