#!/usr/bin/env perl
use Mojo::Base -strict;

my $file = defined $ARGV[0] ? $ARGV[0] : 'inputs/day1';
$file = "inputs/day1-$file" if $file =~ /test/;
open(my $fh, '<', $file) or die $!;
while (<$fh>) {
    chomp;
    my @chars = split //;
    my $l = 0;
    my $i = 0;
    for (@chars) {
        $l = $_ eq ')' ? $l - 1 : $l + 1;
        $i++;
        if ($l == -1) {
            say $i;
            exit;
        }
    }
}
