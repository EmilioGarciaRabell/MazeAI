import pygame
import random

# Dimensions
WIDTH = 300
HEIGHT = 600

# Initialize the game environment
pygame.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
clock = pygame.time.Clock()
player_size = 20


def outer_bounds(player_x, player_y):
    # Check if the player is between the window dimensions
    if player_x >= WIDTH - player_size:
        player_x = WIDTH - player_size
    if player_x < 0:
        player_x = 0
    if player_y > HEIGHT - player_size:
        player_y = HEIGHT - player_size
    if player_y < 0:
        player_y = 0
    return player_x, player_y


def create_maze():
    maze = []

    width = 0
    height = 0

    num_of_walls = random.randrange(0, 100)

    for wall in range(0,num_of_walls):
        maze.append(pygame.Rect()) # create a thing that can come up with the wall placement



    # maze = [
    #     pygame.Rect(50, 50, 200, player_size - 5),
    #     pygame.Rect(50, 100, player_size - 5, 200),
    #     pygame.Rect(230, 100, player_size - 5, 200),
    #     pygame.Rect(50, 300, 200, player_size - 5)
    # ]

    return maze


def main():
    player_x = WIDTH // 2
    player_y = HEIGHT // 2
    running = True
    while running:
        screen.fill("white")
        player = pygame.draw.rect(screen, "blue", pygame.Rect(player_x, player_y, player_size, player_size))
        for wall in maze:
            pygame.draw.rect(screen, (0, 0, 0), wall)
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:

                if event.key == pygame.K_UP:
                    player_y -= HEIGHT
                elif event.key == pygame.K_DOWN:
                    player_y += HEIGHT
                elif event.key == pygame.K_LEFT:
                    player_x -= WIDTH
                elif event.key == pygame.K_RIGHT:
                    player_x += WIDTH
                # Check that the player is within the bounds
                player_x, player_y = outer_bounds(player_x, player_y)

                maze = create_maze()
                for block in maze:
                    if player.colliderect(block):
                        player_x = 0
                        player_y = 0

            if event.type == pygame.QUIT:
                running = False

        pygame.display.flip()
        clock.tick(60)


main()
pygame.quit()
