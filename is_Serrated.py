{
  "nbformat": 4,
  "nbformat_minor": 0,
  "metadata": {
    "colab": {
      "provenance": [],
      "authorship_tag": "ABX9TyP35Oy8ClyX0zhKuzEwnJe2",
      "include_colab_link": true
    },
    "kernelspec": {
      "name": "python3",
      "display_name": "Python 3"
    },
    "language_info": {
      "name": "python"
    }
  },
  "cells": [
    {
      "cell_type": "markdown",
      "metadata": {
        "id": "view-in-github",
        "colab_type": "text"
      },
      "source": [
        "<a href=\"https://colab.research.google.com/github/thomasvicente/Class-Projects/blob/main/is_Serrated.py\" target=\"_parent\"><img src=\"https://colab.research.google.com/assets/colab-badge.svg\" alt=\"Open In Colab\"/></a>"
      ]
    },
    {
      "cell_type": "code",
      "execution_count": 7,
      "metadata": {
        "id": "pCQJRgEuFLlU",
        "outputId": "daa6291a-b195-4fea-9e60-56c4faae4c8f",
        "colab": {
          "base_uri": "https://localhost:8080/"
        }
      },
      "outputs": [
        {
          "output_type": "stream",
          "name": "stdout",
          "text": [
            "0.085874\n"
          ]
        }
      ],
      "source": [
        "import random\n",
        "\n",
        "def is_serrated(sequence):\n",
        "    sign = 1 \n",
        "    for i in range(1, len(sequence)):\n",
        "        if sequence[i] == sequence[i-1]:\n",
        "            return False\n",
        "        if (sequence[i] - sequence[i-1]) * sign < 0: \n",
        "            return False\n",
        "        sign = -sign\n",
        "    return True\n",
        "\n",
        "\n",
        "if __name__ == '__main__':\n",
        "  trials = 10**6\n",
        "  serrated_count = 0\n",
        "  my_list = [1,2,3,4,5,6]\n",
        "  for _ in range(trials):\n",
        "      sequence = [random.choice(my_list) for _ in range(5)]\n",
        "      if is_serrated(sequence):\n",
        "          serrated_count += 1\n",
        "\n",
        "  probability = serrated_count/trials\n",
        "  print(probability)"
      ]
    }
  ]
}