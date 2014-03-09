package com.FriedTaco.taco.godPowers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Hashtable;

public class Jesus {
    int[] raftX = new int[25];
    int[] raftY = new int[25];
    int[] raftZ = new int[25];
    @SuppressWarnings("rawtypes")
    public static Hashtable rafts = new Hashtable();
    Player player;

    public Jesus() {
    }

    public class RaftPiece {
        public RaftPiece(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;

        }

        int x, y, z, type = 0;
        boolean made = false;
        boolean destroyed = false;
    }

    public class Raft {
        public RaftPiece[] raft =
                {
                        new RaftPiece(2, -1, 2),
                        new RaftPiece(2, -1, 1),
                        new RaftPiece(2, -1, 0),
                        new RaftPiece(2, -1, -1),
                        new RaftPiece(2, -1, -2),
                        new RaftPiece(1, -1, 2),
                        new RaftPiece(1, -1, 1),
                        new RaftPiece(1, -1, 0),
                        new RaftPiece(1, -1, -1),
                        new RaftPiece(1, -1, -2),
                        new RaftPiece(0, -1, 2),
                        new RaftPiece(0, -1, 1),
                        new RaftPiece(0, -1, 0),
                        new RaftPiece(0, -1, -1),
                        new RaftPiece(0, -1, -2),
                        new RaftPiece(-1, -1, 2),
                        new RaftPiece(-1, -1, 1),
                        new RaftPiece(-1, -1, 0),
                        new RaftPiece(-1, -1, -1),
                        new RaftPiece(-1, -1, -2),
                        new RaftPiece(-2, -1, 2),
                        new RaftPiece(-2, -1, 1),
                        new RaftPiece(-2, -1, 0),
                        new RaftPiece(-2, -1, -1),
                        new RaftPiece(-2, -1, -2)
                };

        public void makeJesusRaft(Player player) {
            for (int i = 0; i < raft.length; i++) {
                Block block = player.getWorld().getBlockAt(((int) player.getLocation().getX() + raft[i].x), ((int) player.getLocation().getY() + raft[i].y), ((int) player.getLocation().getZ() + raft[i].z));
                if (block.getType() == Material.WATER | block.getType() == Material.STATIONARY_WATER) {
                    raftX[i] = (int) player.getLocation().getX() + raft[i].x;
                    raftY[i] = (int) player.getLocation().getY() + raft[i].y;
                    raftZ[i] = (int) player.getLocation().getZ() + raft[i].z;
                    raft[i].made = true;
                    block.setType(Material.ICE);
                } else if (block.getType() == Material.LAVA | block.getType() == Material.STATIONARY_LAVA) {
                    raftX[i] = (int) player.getLocation().getX() + raft[i].x;
                    raftY[i] = (int) player.getLocation().getY() + raft[i].y;
                    raftZ[i] = (int) player.getLocation().getZ() + raft[i].z;
                    raft[i].made = true;
                    block.setType(Material.OBSIDIAN);
                } else {
                    raft[i].made = false;
                }
            }
        }

        public void destroyJesusRaft(Player player) {
            for (int i = 0; i < raft.length; i++) {
                Block block = player.getWorld().getBlockAt(((int) raftX[i]), ((int) raftY[i]), ((int) raftZ[i]));
                if (block.getType() == Material.ICE) {
                    block.setType(Material.WATER);
                } else if (block.getType() == Material.OBSIDIAN) {
                    block.setType(Material.LAVA);
                }
                if (raft[i].made) {
                    raft[i].made = false;
                }
            }
        }
    }
}
