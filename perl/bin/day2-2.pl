#!/usr/bin/env perl
use Mojo::Base -strict;

use Array::Utils qw(:all);
use List::AllUtils qw(:all);
use Memoize;

my $file = defined $ARGV[0] ? $ARGV[0] : 'inputs/day2';
$file = "inputs/day2-$file" if $file =~ /test/;
open(my $fh, '<', $file) or die $!;

my $total = 0;

while (<$fh>) {
    chomp;
    my ($l, $w, $h) = split /x/;
    my $longest = max($l, $w, $h);
    my $short_perim = (sum($l, $w, $h) - $longest) * 2;
    my $bow = $l * $w * $h;
    $total += ($short_perim + $bow);
}

say $total;
