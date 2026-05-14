import { render, screen } from "@testing-library/react";
import { describe, it, expect } from 'vitest';

import App from "/src/App";

describe("App component", () => {
  it("Renders container element", () => {
    
    render(<App />);

    expect(screen.getByRole('main')).toBeInTheDocument();
  });
});