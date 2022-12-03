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
    my $vol = 2*$l*$w + 2*$w*$h + 2*$h*$l;
    my $slack = min ($l * $w, $w * $h, $h * $l);
    $total += ($vol + $slack);
}

say $total;
