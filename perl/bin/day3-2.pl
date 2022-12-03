#!/usr/bin/env perl
use Mojo::Base -strict;

my $file = defined $ARGV[0] ? $ARGV[0] : 'inputs/day3';
$file = "inputs/day3-$file" if $file =~ /test/;
open(my $fh, '<', $file) or die $!;

my $moves = {
    '^' => [1, 0],
    '>' => [0, 1],
    '<' => [0, -1],
    'v' => [-1, 0],
};
my $grid = {};

while (<$fh>) {
    chomp;
    my @moves = split //;
    my @s_curr = (0, 0);
    my @r_curr = (0, 0);

    my $i = 0;
    for (@moves) {
        if ($i % 2 == 0) {
            my ($x, $y) = @s_curr;
            $grid->{$x, $y}++;
            @s_curr = move(\@s_curr, $_);
        } else {
            my ($x, $y) = @r_curr;
            $grid->{$x, $y}++;
            @r_curr = move(\@r_curr, $_);
        }
        $i++;
    }

    say scalar keys %$grid;
}

sub move {
    my ($curr, $move) = @_;
    my @new = @{$moves->{$move}};
    return ($curr->[0] + $new[0], $curr->[1] + $new[1]);
}
